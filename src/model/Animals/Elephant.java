package model.Animals;

import model.Animal;

public class Elephant extends Animal {
    public Elephant(int side){
        if (side==0){
            this.setLocation(6,0);
        }
        else {
            this.setLocation(2,6);
        }
        this.setCanJump(false);
        this.setCanSwim(false);
        this.setSide(side);
        this.setRank(8);
    }
    public String getName(){return "象";}

}
