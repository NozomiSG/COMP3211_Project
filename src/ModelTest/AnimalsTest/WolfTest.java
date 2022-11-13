package ModelTest.AnimalsTest;

import model.Animals.Wolf;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class WolfTest {
    Wolf wolf0;//wolf for side 0
    Wolf wolf1;//wolf for side 1

    @Before
    public void constructor() {
        wolf0 = new Wolf(0);
        wolf1 = new Wolf(1);
    }

    @Test
    public void statusTest() {
        int [] location0=wolf0.getLocation();
        assertTrue(location0[0] == 6 && location0[1]==2);
        int [] location1=wolf1.getLocation();
        assertTrue(location1[0] == 2 && location1[1]==4);
        assertEquals(wolf0.getRank(),4);
        assertEquals(wolf1.getRank(),4);
        assertFalse(wolf0.isCanJump() || wolf1.isCanJump());
        assertFalse(wolf0.isCanSwim() || wolf1.isCanSwim());
    }

    @Test
    public void nameTest() {
        assertEquals(wolf0.getName(),"Wolf");
        assertEquals(wolf1.getName(),"Wolf");
    }

}