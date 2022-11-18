package model;

import static model.ChessBoard.board;
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
        this.canSwim=canSwim;
    }

    public boolean isCanSwim() {return canSwim;}

    public boolean move(Square s){
        if(this.checkMoveLegal(s, true)) {
            if (s.getAnimal() != null) {
                s.getAnimal().setAlive(false);
                s.setAnimal(null);
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

    public boolean isCanCapture(Animal enemy, boolean ifPrint){
        String type = board.getSquareByAnimal(this).getType();
        String type_enemy = board.getSquareByAnimal(enemy).getType();
        if(enemy.side==this.side) {
            monitor.printWarning("You can't overlap with your animal!", ifPrint);
            return false;
        }
        else if (type_enemy.equals("河")&& type.equals("　")) {
            monitor.printWarning("Animal can't capture enemy on the land!", ifPrint);
            return false;
        }
        else if(type_enemy.equals("　") && type.equals("河")) {
            monitor.printWarning("Animal can't capture enemy in the river!", ifPrint);
            return false;
        }
        else if(type_enemy.equals("陷"))
            return true;
        else if(this.getName().equals("鼠") && enemy.getName().equals("象"))
            return true;
        else if(this.getName().equals("象") && enemy.getName().equals("鼠")) {
            monitor.printWarning("Elephant cannot capture rat!", ifPrint);
            return false;
        }
        else {
            if (enemy.rank > this.rank) {
                monitor.printWarning("You can not capture animal with higher rank!", ifPrint);
                return false;
            }
            return true;
        }

    }

    public boolean checkJumpLegal(Square s, boolean ifPrint) {
        if (!(this.getName().equals("獅")||this.getName().equals("虎"))) {
            monitor.printWarning("This animal cannot jump!", ifPrint);
            return false;
        }
        if(s.getAnimal()!=null)
            if(!this.isCanCapture(s.getAnimal(), ifPrint))
                return false;
        int x0=this.x;
        int x1=s.getLocation()[0];
        int y0=this.y;
        int y1=s.getLocation()[1];
        if(x0==x1) {
            // Check whether there is rat between y0 and y1
            if (y0 > y1) {
                int large = y0;
                y0 = y1;
                y1 = large;
            }
            for (int j = y0+1; j < y1; j++) {
                if (board.getSquares()[x0][j].getAnimal() != null && board.getSquares()[x0][j].getAnimal().getName().equals("鼠")) {
                    monitor.printWarning("You can't jump over rat in the river!", ifPrint);
                    return false;
                }
            }
        }
        else if(y0==y1) {
            // Check whether there is rat between x0 and x1
            if (x0 > x1) {
                int large = x0;
                x0 = x1;
                x1 = large;
            }
            for (int i = x0 + 1; i < x1; i++) {
                if (board.getSquares()[i][y0].getAnimal() != null && board.getSquares()[i][y0].getAnimal().getName().equals("鼠")) {
                    monitor.printWarning("You can't jump over rat in the river!", ifPrint);
                    return false;
                }
            }
        }
        return true;
    }//check if it can jump to square s, s should be opposite river

    public boolean checkSwimLegal(Square s, boolean ifPrint) {
        if (this.canSwim) {
            if (s.getAnimal() != null) {
                return isCanCapture(s.getAnimal(), ifPrint);
            } else
                return true;
        }
        else {
            monitor.printWarning("This animal cannot swim!", ifPrint);
            return false;
        }
    }//check if it can swim to square s, if rat in river, use this function

    public boolean checkMoveLegal(Square s, boolean ifPrint) {
        int x=s.getLocation()[0];
        int y=s.getLocation()[1];
        // Check alive
        if (!getAlive()) {
            monitor.printWarning(this.getName()+" has been slain!", ifPrint);
            return false;
        }
        //boundary
        if(x == this.x && y == this.y) {
            monitor.printWarning("Your animal cannot move outside the boundaries!", ifPrint);
            return false;
        }
        //can not move into player's own den
        if(this.getSide()==0){
            if (x == 8 && y == 3) {
                monitor.printWarning("Your animal cannot move into your own den!", ifPrint);
                return false;
            }
        }else{
            if (x == 0 && y == 3) {
                monitor.printWarning("Your animal cannot move into your own den!", ifPrint);
                return false;
            }
        }
        //check swim legality
        if (s.getType().equals("河") || board.getSquareByAnimal(this).getType().equals("河")){
            return  checkSwimLegal(s, ifPrint);
        }
        //check jump legality, regard the expected destination as the adjacent one in river, not the real destination.
        if( x-this.x + y-this.y <-1 || x-this.x + y-this.y >1) {
            return checkJumpLegal(s, ifPrint);

        }
        if (s.getAnimal()!=null)
            return isCanCapture(s.getAnimal(), ifPrint);
        return true;
    }//check if it can move to square s whose location is (x,y), s should be an adjacent square of this animal (including capture)

    abstract public String getName();
}
