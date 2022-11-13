package model.Animals;

import model.Animal;

public class Dog extends Animal {
    public Dog(int side){
        if (side==0) {
            this.setLocation(7,5);
        }
        else {
            this.setLocation(1, 1);
        }
        this.setCanJump(false);
        this.setCanSwim(false);
        this.setSide(side);
        this.setRank(3);
    }
    public String getName(){return "狗";}
}
