package ModelTest.AnimalsTest;

import model.Animals.Rat;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class RatTest {
    Rat rat0;//rat for side 0
    Rat rat1;//rat for side 1

    @Before
    public void constructor() {
        rat0 = new Rat(0);
        rat1 = new Rat(1);
    }

    @Test
    public void statusTest() {
        int [] location0=rat0.getLocation();
        assertTrue(location0[0] == 6 && location0[1]==6);
        int [] location1=rat1.getLocation();
        assertTrue(location1[0] == 2 && location1[1]==0);
        assertEquals(rat0.getRank(),1);
        assertEquals(rat1.getRank(),1);
        assertFalse(rat0.isCanJump() || rat1.isCanJump());
        assertTrue(rat0.isCanSwim() && rat1.isCanSwim());
    }

    @Test
    public void nameTest() {
        assertEquals(rat0.getName(),"鼠");
        assertEquals(rat1.getName(),"鼠");
    }


}