package model.Animals;

import model.Animal;

public class Leopard extends Animal {
    public Leopard(int side){
        if (side==0){
            this.setLocation(6,4);
        }
        else {
            this.setLocation(2,2);
        }
        this.setCanJump(false);
        this.setCanSwim(false);
        this.setSide(side);
        this.setRank(5);
    }
    public String getName(){return "豹";}
}
