package model.Animals;

import model.Animal;

public class Cat extends Animal {
    public Cat(int side){
        if (side==0){
            this.setLocation(7,1);
        }
        else {
            this.setLocation(1,5);
        }
        this.setCanJump(false);
        this.setCanSwim(false);
        this.setSide(side);
        this.setRank(2);
    }//set side

    public String getName(){return "貓";}


}
