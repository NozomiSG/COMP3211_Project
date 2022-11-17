package ModelTest.AnimalsTest;

import model.Animals.Leopard;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class LeopardTest {
    Leopard leopard0;//leopard for side 0
    Leopard leopard1;//leopard for side 1

    @Before
    public void constructor() {
        leopard0 = new Leopard(0);
        leopard1 = new Leopard(1);
    }

    @Test
    public void statusTest() {
        int [] location0=leopard0.getLocation();
        assertTrue(location0[0] == 6 && location0[1]==4);
        int [] location1=leopard1.getLocation();
        assertTrue(location1[0] == 2 && location1[1]==2);
        assertEquals(leopard0.getRank(),5);
        assertEquals(leopard1.getRank(),5);
        assertFalse(leopard0.isCanJump() || leopard1.isCanJump());
        assertFalse(leopard0.isCanSwim() || leopard1.isCanSwim());
    }

    @Test
    public void nameTest() {
        assertEquals(leopard0.getName(),"豹");
        assertEquals(leopard1.getName(),"豹");
    }

}