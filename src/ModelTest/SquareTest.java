package ModelTest;

import model.Animals.Cat;
import model.Square;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class SquareTest {
     Square testSquare1;
     Square testSquare2;
     Square testSquare3;
     Square testSquare4;


    @Before
    public void SquareConstructor() {
        testSquare1 = new Square(3,0,"Den");
        testSquare2 = new Square(3,1,"Trap");
        testSquare3 = new Square(1,5,"River");
        testSquare4 = new Square(6,0,"　");
    }

    @Test
    public void setLocationTest() {
        //test setLocationTest()
        testSquare1.setLocation(0,0);
        assertEquals(0, testSquare1.getLocation()[0]);
        assertEquals(0, testSquare1.getLocation()[1]);
    }

    @Test
    public void getLocationTest() {
        //test getLocationTest()
        assertEquals(3, testSquare1.getLocation()[0]);
        assertEquals(0, testSquare1.getLocation()[1]);
        assertEquals(3, testSquare2.getLocation()[0]);
        assertEquals(1, testSquare2.getLocation()[1]);
        assertEquals(1, testSquare3.getLocation()[0]);
        assertEquals(5, testSquare3.getLocation()[1]);
        assertEquals(6, testSquare4.getLocation()[0]);
        assertEquals(0, testSquare4.getLocation()[1]);
    }

    @Test
    public void setTypeTest() {
        //set getType(), i.e. which type the square is
        testSquare1.setType("Test");
        assertEquals("Test", testSquare1.getType());
    }

    @Test
    public void getTypeTest() {
        //test getType(), i.e. which type the square is
        assertEquals("Den", testSquare1.getType());
        assertEquals("Trap", testSquare2.getType());
        assertEquals("River", testSquare3.getType());
        assertEquals("　", testSquare4.getType());
    }



    @Test
    public void getAnimalTest1() {
        //test getAnimal(), i.e. test which animal is on this square
        //case1: no animal is on this square
        assertNull(testSquare2.getAnimal());
    }

    @Test
    public void setAnimalTest() {
        //Cat occupies this square
        testSquare1.setAnimal(new Cat(0));
        assertTrue(testSquare1.getAnimal() instanceof Cat);
    }

    @Test
    public void getAnimalTest2() {
        //test getAnimal(), i.e. test which animal is on this square
        //case1: animal is on this square
        //Cat occupies this square
        testSquare1.setAnimal(new Cat(0));
        assertTrue(testSquare1.getAnimal() instanceof Cat);
    }


}