package model.Animals;

import model.Animal;

public class Lion extends Animal {
    public Lion(int side){
        if (side==0){
            this.setLocation(8,6);
        }
        else {
            this.setLocation(0,0);
        }
        this.setCanJump(true);
        this.setCanSwim(false);
        this.setSide(side);
        this.setRank(7);
    }
    public String getName(){return "獅";}
}
