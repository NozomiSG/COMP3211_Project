package ModelTest.AnimalsTest;

import model.Animals.Cat;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class CatTest {
    Cat cat0;//cat for side 0
    Cat cat1;//cat for side 1

    @Before
    public void constructor() {
        cat0 = new Cat(0);
        cat1 = new Cat(1);
    }

    @Test
    public void statusTest() {
        int[] location0 = cat0.getLocation();
        assertTrue(location0[0] == 7 && location0[1] == 1);
        int[] location1 = cat1.getLocation();
        assertTrue(location1[0] == 1 && location1[1] == 5);
        assertEquals(cat0.getRank(), 2);
        assertEquals(cat1.getRank(), 2);
        assertFalse(cat0.isCanJump() || cat1.isCanJump());
        assertFalse(cat0.isCanSwim() || cat1.isCanSwim());
    }

    @Test
    public void nameTest() {
        assertEquals(cat0.getName(), "貓");
        assertEquals(cat1.getName(), "貓");
    }


}


