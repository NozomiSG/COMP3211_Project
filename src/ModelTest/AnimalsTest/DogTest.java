package ModelTest.AnimalsTest;

import model.Animals.Dog;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class DogTest {
    Dog dog0;//dog for side 0
    Dog dog1;//dog for side 1

    @Before
    public void constructor() {
        dog0 = new Dog(0);
        dog1 = new Dog(1);
    }

    @Test
    public void statusTest() {
        int [] location0=dog0.getLocation();
        assertTrue(location0[0] == 7 && location0[1]==5);
        int [] location1=dog1.getLocation();
        assertTrue(location1[0] == 1 && location1[1]==1);
        assertEquals(dog0.getRank(),3);
        assertEquals(dog1.getRank(),3);
        assertFalse(dog0.isCanJump() || dog1.isCanJump());
        assertFalse(dog0.isCanSwim() || dog1.isCanSwim());
    }

    @Test
    public void nameTest() {
        assertEquals(dog0.getName(),"Dog");
        assertEquals(dog1.getName(),"Dog");
    }


}