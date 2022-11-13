package model;

import static model.ChessBoard.board;

public abstract class Animal {
    private int rank; //1-8, 0 if in dens
    private int x,y;
    private int side;
    private boolean canSwim;
    private boolean canJump;


    protected Animal() {}


    public int getRank() {return this.rank;}

    public void setRank(int r){
        this.rank=r;
    }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setSide(int side) {this.side = side;}

    public int getSide() {return this.side;}

    public int[] getLocation() {
        return new int[] { this.x, this.y,};
    }

    public void setCanJump(boolean canJump) {
        canJump = this.getName().equals("虎") | this.getName().equals("獅");
        this.canJump=canJump;
    }

    public boolean isCanJump() {return canJump;}

    public void setCanSwim(boolean canSwim) {
        canSwim = this.getName().equals("鼠");
        this.canSwim=canSwim;
    }

    public boolean isCanSwim() {return canSwim;}

    //discard, use move in controller
//    public void move(int direction){
////        monitor.printChessboard(board);
////        System.out.println(board.getSquareByAnimal(this).getLocation()[0]);
////        System.out.println(board.getSquareByAnimal(this).getLocation()[1]);
////        for (int i=0;i<9;i++){
////            for (int j=0;j<7;j++){
////                System.out.print(board.getSquares()[i][j]);
////            }
////            System.out.println();
////        }
//        int x=this.x, y=this.y;
//        if(this.getSide()==0){
//            if (direction==0)
//                x=x-1;
//            else if (direction==1)
//                x=x+1;
//            else if (direction==2)
//                y=y-1;
//            else if (direction==3)
//                y=y+1;
//        }else{
//            if (direction==0)
//                x=x+1;
//            else if (direction==1)
//                x=x-1;
//            else if (direction==2)
//                y=y+1;
//            else if (direction==3)
//                y=y-1;
//        }
//        if(x<0|x>8)
//            x=this.x;
//        else if(y<0|y>6)
//            y=this.x;
//        else
//            if(this.checkMoveLegal(board.getSquares()[x][y]))
//                if(this.checkJumpLegal(board.getSquares()[x][y]))
//                    if (x==this.x)
//                        y=y+2;
//                    else if(y==this.x)
//                        x=x+3;
//            board.getSquares()[this.x][this.y].setAnimal(null);
//            board.getSquares()[x][y].setAnimal(this);
//        this.setLocation(x,y);
//
//    }//0:up 1:down 2:left 3:right


    public boolean isCanCapture(Animal a){
        int xa=a.getLocation()[0];
        int ya=a.getLocation()[1];
        String type = board.getSquareByAnimal(this).getType();
        String typea = board.getSquareByAnimal(a).getType();

        if(a.side==this.side)
            return false;
        else
            if (typea.equals("河")&& type.equals(" "))
                return false;
            else if(typea.equals(" ") && type.equals("河"))
                return false;
            else if(typea.equals("陷"))
                return true;
            else
                if(this.getName().equals("鼠") && a.getName().equals("象"))
                    return true;
                else if (a.rank>this.rank)
                    return false;
                else
                    return true;
    }


    public boolean checkJumpLegal(Square s) {
        if (!(this.getName().equals("獅")||this.getName().equals("虎")))
            return false;
        if(!s.getType().equals("河"))
            return false;
        if(s.getAnimal()!=null)
            if(!this.isCanCapture(s.getAnimal()))
                return false;

        int x0=this.x;
        int x1=s.getLocation()[0];
        int y0=this.y;
        int y1=s.getLocation()[1];
        if(x0==x1)
            for(int j=y1;j<=y1+1;j++){
                if(board.getSquares()[x0][j].getAnimal()!=null &&board.getSquares()[x0][j].getAnimal().getName().equals("鼠"))
                    return false;
            }
        else if(y0==y1)
            for(int i=x1;i<=x1+2;i++){
                if(board.getSquares()[i][y0].getAnimal()!=null && board.getSquares()[i][y0].getAnimal().getName().equals("鼠"))
                    return false;
            }
        return true;
    }//check if it can jump to square s, s should be opposite river

    public boolean checkSwimLegal(Square s) {
        if (this.canSwim)
            if (s.getAnimal() != null && !(s.getType().equals(board.getSquareByAnimal(this).getType())))
                return false;
            else
                return true;
        else
            return false;
    }//check if it can swim to square s, if rat in river, use this function

    public boolean checkMoveLegal(Square s) {
        int x=s.getLocation()[0];
        int y=s.getLocation()[1];

        //must be adjacent
//        if(x!=this.x)//?
//            if(y!=this.y)
//                return false;

        //can not be out of chess board
        if (x<0||x>8)
            return false;
        if (y<0||y>6)
            return false;

        //can not move into player's own den
        if(this.getSide()==0){
            if (x == 8 && y == 3)
                return false;
        }else{
            if (x == 0 && y == 3)
                return false;
        }
        //check swim legality
        if (s.getType().equals("河")){
            return  checkSwimLegal(s);
        }
        //check jump legality, regard the expected destination as the adjacent one in river, not the real destination.
        if( x-this.x + y-this.y <-1 || x-this.x + y-this.y >1) {
            return checkJumpLegal(s);

        }
        if (s.getAnimal()!=null)
            return isCanCapture(s.getAnimal());

        return true;
    }//check if it can move to square s whose location is (x,y), s should be an adjacent square of this animal (including capture)

    public String getName() {return "";}
}
