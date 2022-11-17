package ModelTest.AnimalsTest;

import model.Animals.Elephant;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class ElephantTest {
    Elephant elephant0;//elephant for side 0
    Elephant elephant1;//elephant for side 1

    @Before
    public void constructor() {
        elephant0 = new Elephant(0);
        elephant1 = new Elephant(1);
    }

    @Test
    public void statusTest() {
        int [] location0=elephant0.getLocation();
        assertTrue(location0[0] == 6 && location0[1]==0);
        int [] location1=elephant1.getLocation();
        assertTrue(location1[0] == 2 && location1[1]==6);
        assertEquals(elephant0.getRank(),8);
        assertEquals(elephant1.getRank(),8);
        assertFalse(elephant0.isCanJump() || elephant1.isCanJump());
        assertFalse(elephant0.isCanSwim() || elephant1.isCanSwim());
    }

    @Test
    public void nameTest() {
        assertEquals(elephant0.getName(),"象");
        assertEquals(elephant1.getName(),"象");
    }

}