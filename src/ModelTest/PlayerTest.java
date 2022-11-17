package ModelTest;

import model.Animals.*;

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
        assertTrue(testPlayer1.getAnimals()[1] instanceof Cat);
        assertTrue(testPlayer1.getAnimals()[2] instanceof Dog);
        assertTrue(testPlayer1.getAnimals()[3] instanceof Wolf);
        assertTrue(testPlayer1.getAnimals()[4] instanceof Leopard);
        assertTrue(testPlayer1.getAnimals()[5] instanceof Tiger);
        assertTrue(testPlayer1.getAnimals()[6] instanceof Lion);
        assertTrue(testPlayer1.getAnimals()[7] instanceof Elephant);
    }

    @Test
    public void getSideTest() {
        //test getSide()
        assertEquals(0, testPlayer1.getSide());
        assertEquals(1, testPlayer2.getSide());
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