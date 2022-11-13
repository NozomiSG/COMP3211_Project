package model;


public class Square {
    private int x;
    private int y;
    private String type;
    private Animal animal = null;

    public Square (int x, int y, String squareType){
        this.x = x;
        this.y = y;
        this.type = squareType;
    }

    public int[] getLocation() {
        return new int[]{this.x, this.y};
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setType(String type) {this.type = type;}

    public String getType() {return type;}

    public void setAnimal(Animal animal) {this.animal=animal;}

    public Animal getAnimal() {return this.animal;}

    public String printSquare() {
        Animal currentAnimal = getAnimal();
        if (currentAnimal == null)
            return " " + getType() + " ";
        int side = currentAnimal.getSide();
        if (side == 1) {
            return currentAnimal.getRank() + currentAnimal.getName() + " ";
        }
        else {
            return " " + currentAnimal.getName() + currentAnimal.getRank();
        }
    }
}
