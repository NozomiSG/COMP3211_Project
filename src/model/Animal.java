package model;

import model.Square;
import controller.gameController;

import static model.ChessBoard.board;

import model.Animals.*;
import view.monitor;

public abstract class Animal {
    private int rank; //1-8, 0 if in dens
    private int x,y;
    private int side;
    private boolean canSwim;
    private boolean canJump;
    private boolean alive = true;


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

    public void setAlive(boolean isAlive){
        this.alive = isAlive;
    }
    public boolean getAlive(){
        return this.alive;
    }
    public void setCanSwim(boolean canSwim) {
        canSwim = this.getName().equals("鼠");
        this.canSwim=canSwim;
    }

    public boolean isCanSwim() {return canSwim;}


    public boolean move(Square s){
        if(this.checkMoveLegal(s)) {
            if (s.getAnimal() != null) {
                s.getAnimal().setAlive(false);
            }
            //clear the animal at the original location
            board.getSquares()[this.getLocation()[0]][this.getLocation()[1]].setAnimal(null);
            //set the new location to animal
            this.setLocation(s.getLocation()[0], s.getLocation()[1]);
            //set animal in new location in chessboard
            board.getSquares()[s.getLocation()[0]][s.getLocation()[1]].setAnimal(this);
            return true;
        }
        else return false;
    }


    public boolean isCanCapture(Animal enemy){
        String type = board.getSquareByAnimal(this).getType();
        String type_enemy = board.getSquareByAnimal(enemy).getType();

        if(enemy.side==this.side)
            return false;
        else if (type_enemy.equals("河")&& type.equals("　"))
            return false;
        else if(type_enemy.equals("　") && type.equals("河"))
            return false;
        else if(type_enemy.equals("陷"))
            return true;
        else if(this.getName().equals("鼠") && enemy.getName().equals("象"))
            return true;
        else
            return enemy.rank <= this.rank;
    }


    public boolean checkJumpLegal(Square s) {
        if (!(this.getName().equals("獅")||this.getName().equals("虎")))
            return false;
        if(s.getAnimal()!=null)
            if(!this.isCanCapture(s.getAnimal()))
                return false;

        int x0=this.x;
        int x1=s.getLocation()[0];
        int y0=this.y;
        int y1=s.getLocation()[1];
        if(x0==x1)
            for(int j=y1+1;j<=y1+2;j++){
                if(board.getSquares()[x0][j].getAnimal()!=null &&board.getSquares()[x0][j].getAnimal().getName().equals("鼠"))
                    return false;
            }
        else if(y0==y1)
            for(int i=x1+1;i<=x1+3;i++){
                if(board.getSquares()[i][y0].getAnimal()!=null && board.getSquares()[i][y0].getAnimal().getName().equals("鼠"))
                    return false;
            }
        return true;
    }//check if it can jump to square s, s should be opposite river

    public boolean checkSwimLegal(Square s) {

        if (this.canSwim) {
            if (s.getAnimal() != null) {
                if (s.getAnimal().rank == 1 || s.getAnimal().rank == 8) {
                    if (isCanCapture(s.getAnimal())) return true;
                    else return false;
                } else return false;
            } else
                return true;
        }
        else
            return false;
    }//check if it can swim to square s, if rat in river, use this function

    public boolean checkMoveLegal(Square s) {
        int x=s.getLocation()[0];
        int y=s.getLocation()[1];

        //boundary
        if(x == this.x && y == this.y) return false;


        //can not move into player's own den
        if(this.getSide()==0){
            if (x == 8 && y == 3)
                return false;
        }else{
            if (x == 0 && y == 3)
                return false;
        }
        //check swim legality
        if (s.getType().equals("河") || board.getSquareByAnimal(this).getType().equals("河")){
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
