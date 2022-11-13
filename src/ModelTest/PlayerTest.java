package ModelTest;

import model.Animals.Rat;
import model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class PlayerTest {
    Player testPlayer1;
    Player testPlayer2;

    @Before
    public void playerConstructor(){
        testPlayer1 = new Player(0);
        testPlayer2 = new Player(1);
    }

    @Test
    public void getAnimalsTest() {
        //test getAnimals()
        assertTrue(testPlayer1.getAnimals()[0] instanceof Rat);
    }

    @Test
    public void getSideTest() {
        //test getSide()
        assertEquals(0, testPlayer1.getSide());
        assertEquals(1, testPlayer2.getSide());
    }

    @Test
    public void animalMoveTest() {
        //test animalMove(Animal animal, String dir)
        int originalX = testPlayer1.getAnimals()[0].getLocation()[0];
        int originalY = testPlayer1.getAnimals()[0].getLocation()[1];
        testPlayer1.animalMove(testPlayer1.getAnimals()[0],"up" );
        int nextX = testPlayer1.getAnimals()[0].getLocation()[0];
        int nextY = testPlayer1.getAnimals()[0].getLocation()[1];

        assertEquals(originalX, nextX);
        assertEquals(originalY+1, nextY);
    }

    @Test
    public void setSideTest() {
        //test setSide(String side)
        // change side
        testPlayer1.setSide(1);
        testPlayer2.setSide(0);
        assertEquals(1, testPlayer1.getSide());
    }







}