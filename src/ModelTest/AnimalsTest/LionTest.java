package ModelTest.AnimalsTest;

import model.Animals.Lion;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class LionTest {
    Lion lion0;//lion for side 0
    Lion lion1;//lion for side 1

    @Before
    public void constructor() {
        lion0 = new Lion(0);
        lion1 = new Lion(1);
    }

    @Test
    public void statusTest() {
        int [] location0=lion0.getLocation();
        assertTrue(location0[0] == 8 && location0[1]==6);
        int [] location1=lion1.getLocation();
        assertTrue(location1[0] == 0 && location1[1]==0);
        assertEquals(lion0.getRank(),7);
        assertEquals(lion1.getRank(),7);
        assertTrue(lion0.isCanJump() && lion1.isCanJump());
        assertFalse(lion0.isCanSwim() || lion1.isCanSwim());
    }

    @Test
    public void nameTest() {
        assertEquals(lion0.getName(),"Lion");
        assertEquals(lion1.getName(),"Lion");
    }

}