package ModelTest;

import model.Animals.*;
import model.Square;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;
import static model.ChessBoard.board;

public class AnimalTest {

    //ChessBoard testBoard;
    Rat rat0;
    Rat rat1;
    Dog dog1;
    Lion lion0;
    Elephant elephant0;
    Cat cat1;
    Elephant elephant1;
    Tiger tiger1,tiger0;


    @Before
    public void ConstructorAndInitialize() {
        //testBoard = new ChessBoard();

        for (int i=0; i<9; i++) {
            for (int j=0; j<7; j++) {
                if (j==3 &&(i==0||i==8))
                    board.getSquares()[i][j] = new Square(i, j, "穴");
                else if (((i==0||i==8)&&(j==2||j==4))||(j==3 &&(i==1||i==7)))
                    board.getSquares()[i][j] = new Square(i, j, "陷");
                else if ((i<=5&&i>=3)&&(j==1||j==2||j==4||j==5))
                    board.getSquares()[i][j] = new Square(i, j, "河");
                else
                    board.getSquares()[i][j] = new Square(i, j, " ");
            }
        }

        //set animal locations
        rat0 = new Rat(0);
        rat1 = new Rat(1);
        dog1 = new Dog(1);
        lion0 = new Lion(0);
        elephant0 = new Elephant(0);
        cat1 = new Cat(1);
        elephant1 = new Elephant(1);
        tiger1 = new Tiger(1);
        tiger0 = new Tiger(0);

        lion0.setLocation(3,0);
        rat0.setLocation(3,1);
        rat1.setLocation(4,2);
        elephant0.setLocation(1,3);
        elephant1.setLocation(2,1);
        dog1.setLocation(2,3);
        cat1.setLocation(3,3);
        tiger1.setLocation(0,4);
        tiger0.setLocation(6,5);

        board.getSquares()[3][0].setAnimal(lion0);
        board.getSquares()[3][1].setAnimal(rat0);
        board.getSquares()[4][2].setAnimal(rat1);
        board.getSquares()[1][3].setAnimal(elephant0);
        board.getSquares()[2][1].setAnimal(elephant1);
        board.getSquares()[2][3].setAnimal(dog1);
        board.getSquares()[3][3].setAnimal(cat1);
        board.getSquares()[0][4].setAnimal(tiger1);
        board.getSquares()[6][5].setAnimal(tiger0);

    }

    @Test
    public void moveTest1() {
        //test move(int direction), i.e. check whether this piece is moved successfully
        //rat1 originally at (4,2)
        //rat1 move down is (5,2)
        assertEquals(4,rat1.getLocation()[0]);
        assertEquals(2,rat1.getLocation()[1]);

        rat1.move(board.getSquares()[5][2]);
        assertNull(board.getSquares()[4][2].getAnimal());
        assertEquals(5,rat1.getLocation()[0]);
        assertEquals(2,rat1.getLocation()[1]);

        //rat0 move right is (3,2)
        rat0.move(board.getSquares()[3][2]);
        assertNull(board.getSquares()[3][1].getAnimal());
        assertEquals(3,rat0.getLocation()[0]);
        assertEquals(2,rat0.getLocation()[1]);

        //elephant1 move up (1,1)
        elephant1.move(board.getSquares()[1][1]);
        assertNull(board.getSquares()[2][1].getAnimal());
        assertEquals(1,elephant1.getLocation()[0]);
        assertEquals(1,elephant1.getLocation()[1]);

        //cat1 move down (4,3)
        cat1.move(board.getSquares()[4][3]);
        assertNull(board.getSquares()[3][3].getAnimal());
        assertEquals(4,cat1.getLocation()[0]);
        assertEquals(3,cat1.getLocation()[1]);

        //dog1 move left (2,2)
        dog1.move(board.getSquares()[2][2]);
        assertNull(board.getSquares()[2][3].getAnimal());
        assertEquals(2,dog1.getLocation()[0]);
        assertEquals(2,dog1.getLocation()[1]);

        //elephant0 move left (1,2)
        elephant0.move(board.getSquares()[1][2]);
        assertNull(board.getSquares()[1][3].getAnimal());
        assertEquals(1,elephant0.getLocation()[0]);
        assertEquals(2,elephant0.getLocation()[1]);

        //lion0 move down (4,0)
        lion0.move(board.getSquares()[4][0]);
        assertNull(board.getSquares()[3][0].getAnimal());
        assertEquals(4,lion0.getLocation()[0]);
        assertEquals(0,lion0.getLocation()[1]);

        //tiger0 move right(6,6)
        tiger0.move(board.getSquares()[6][6]);
        assertNull(board.getSquares()[6][5].getAnimal());
        assertEquals(6,tiger0.getLocation()[0]);
        assertEquals(6,tiger0.getLocation()[1]);

        //animal can't move out bound
        tiger1.move(board.getSquares()[0][4]);
        assertEquals(0,tiger1.getLocation()[0]);
        assertEquals(4,tiger1.getLocation()[1]);



        rat1.move(board.getSquares()[4][2]);
        lion0.move(board.getSquares()[3][0]);
        elephant0.move(board.getSquares()[1][3]);
        elephant1.move(board.getSquares()[2][1]);
        rat0.move(board.getSquares()[3][1]);
        cat1.move(board.getSquares()[3][3]);
        dog1.move(board.getSquares()[2][3]);
        tiger1.move(board.getSquares()[0][4]);
        tiger0.move(board.getSquares()[6][5]);

    }
    @Test
    public void moveTest2() {
        //tiger1 can't to go his own den
        assertEquals(1, tiger1.getSide());
        assertFalse(tiger1.checkMoveLegal(board.getSquares()[0][3],false));

        //elephant can't jump
        assertFalse(elephant1.isCanJump());
        assertFalse(elephant1.checkMoveLegal(board.getSquares()[6][1],false));

        //rat0 in intervening river， lion0 can't jump
        assertFalse(lion0.checkJumpLegal(board.getSquares()[3][3],false));
        assertFalse(lion0.checkMoveLegal(board.getSquares()[3][3],false));

        //dog1 can't capture because cat1 is in same side
        assertTrue(dog1.getSide() == cat1.getSide());
        assertFalse(dog1.isCanCapture(cat1,false));
        assertFalse(dog1.checkMoveLegal(board.getSquares()[5][2],false));

        //dog1 capture elephant0 because it's in den
        assertEquals("陷", board.getSquareByAnimal(elephant0).getType());
        assertTrue(dog1.checkMoveLegal(board.getSquares()[1][3],false));
        dog1.move(board.getSquares()[1][3]);
        //elephant0 died
        assertFalse(elephant0.getAlive());


    }
    @Test
    public void moveTest3() {
        //tiger can jump vertical
        assertTrue(tiger0.isCanJump());
        assertTrue(tiger0.checkJumpLegal(board.getSquares()[2][5],false));
        assertTrue(tiger0.checkMoveLegal(board.getSquares()[2][5],false));
        tiger0.move(board.getSquares()[2][5]);

        assertTrue((rat0.getRank() == 1) && (elephant1.getRank() == 8));
        assertFalse(rat0.getSide() == elephant1.getSide());
        //rat0 can capture elephant1
        rat0.move(board.getSquares()[2][1]);
        //elephant1 died
        assertFalse(elephant1.getAlive());

        //lion0 can jump over river horizontal can capture cat1 since rat0 moved away
        assertTrue(lion0.getRank() > cat1.getRank());
        assertTrue(lion0.isCanCapture(cat1,false));
        assertTrue(lion0.checkMoveLegal(board.getSquares()[3][3],false));
        lion0.move(board.getSquares()[3][3]);
        //cat1 died
        assertFalse(cat1.getAlive());

        dog1.move(board.getSquares()[2][3]);
        //dog1 can't move because he can't capture lion0
        assertFalse(dog1.getRank() > lion0.getRank());
        assertFalse(dog1.isCanCapture(lion0,false));
        assertFalse(dog1.checkMoveLegal(board.getSquares()[3][3],false));
        //lion0 can move because he can capture dog1
        assertFalse(dog1.getSide() == lion0.getSide());
        assertTrue(lion0.checkMoveLegal(board.getSquares()[2][3],false));
        lion0.move(board.getSquares()[2][3]);
        //dog1 died
        assertFalse(dog1.getAlive());




    }
    @Test
    public void moveTest4() {
        assertTrue(rat1.isCanSwim());
        rat1.move(board.getSquares()[4][1]);
        rat1.move(board.getSquares()[3][1]);
        //rat1 in river can't capture rat0 on land
        assertFalse(rat1.checkMoveLegal(board.getSquares()[2][1],false));

        rat0.move(board.getSquares()[1][1]);
        rat1.move(board.getSquares()[2][1]);
        //rat1 can capture rat0 because they are both on land
        assertTrue(rat1.checkMoveLegal(board.getSquares()[1][1],false));
        assertFalse(rat0.getAlive());

    }

}