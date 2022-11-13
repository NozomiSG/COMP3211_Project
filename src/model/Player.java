package model;

import model.Animals.*;

public class Player {

    private final Animal[] animals = new Animal[8];
    private int side;

    public Player(int side) {
        this.side = side; // The side of Player1 and Player2 represent by 0 and 1
        animals[0] = new Rat(side);
        animals[1] = new Cat(side);
        animals[2] = new Dog(side);
        animals[3] = new Wolf(side);
        animals[4] = new Leopard(side);
        animals[5] = new Tiger(side);
        animals[6] = new Lion(side);
        animals[7] = new Elephant(side);
        // Init pieces
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    public Animal[] getAnimals() {
        return animals;
    }

    public void animalMove(Animal animal, String dir) {}


}
