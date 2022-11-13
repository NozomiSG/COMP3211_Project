package model.Animals;

import model.Animal;

public class Tiger extends Animal {
    public Tiger(int side){
        if (side==0){
            this.setLocation(8,0);
        }
        else {
            this.setLocation(0,6);
        }
        this.setCanJump(true);
        this.setCanSwim(false);
        this.setSide(side);
        this.setRank(6);
    }
    public String getName(){return "虎";}
}
