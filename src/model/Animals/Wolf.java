package model.Animals;

import model.Animal;

public class Wolf extends Animal {
    public Wolf(int side){
        if (side==0){
            this.setLocation(6,2);
        }
        else {
            this.setLocation(2,4);
        }
        this.setCanJump(false);
        this.setCanSwim(false);
        this.setSide(side);
        this.setRank(4);
    }

    public String getName(){return "狼";}
}
