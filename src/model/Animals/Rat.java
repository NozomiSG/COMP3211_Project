package model.Animals;

import model.Animal;

public class Rat extends Animal {
    public Rat(int side){
        if (side==0){
            this.setLocation(6,6);
        }
        else {
            this.setLocation(2,0);
        }
        this.setCanJump(false);
        this.setCanSwim(true);
        this.setSide(side);
        this.setRank(1);
    }
    public String getName(){return "鼠";}
}
