package ModelTest.AnimalsTest;


import model.Animals.Tiger;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class TigerTest {
    Tiger tiger0;//tiger for side 0
    Tiger tiger1;//tiger for side 1

    @Before
    public void constructor() {
        tiger0 = new Tiger(0);
        tiger1 = new Tiger(1);
    }

    @Test
    public void statusTest() {
        int [] location0=tiger0.getLocation();
        assertTrue(location0[0] == 8 && location0[1]==0);
        int [] location1=tiger1.getLocation();
        assertTrue(location1[0] == 0 && location1[1]==6);
        assertEquals(tiger0.getRank(),6);
        assertEquals(tiger1.getRank(),6);
        assertTrue(tiger0.isCanJump() && tiger1.isCanJump());
        assertFalse(tiger0.isCanSwim() || tiger1.isCanSwim());
    }

    @Test
    public void nameTest() {
        assertEquals(tiger0.getName(),"Tiger");
        assertEquals(tiger1.getName(),"Tiger");
    }

}