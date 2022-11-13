//package ModelTest;
//
//import model.Animals.*;
//import model.Square;
//import org.junit.Before;
//import org.junit.Test;
//
//import static junit.framework.TestCase.*;
//import static model.ChessBoard.board;
//
//public class AnimalTest {
//
//    //ChessBoard testBoard;
//    Rat rat0;
//    Rat rat1;
//    Dog dog1;
//    Lion lion0;
//    Elephant elephant0;
//    Cat cat1;
//    Elephant elephant1;
//    Tiger tiger1;
//
//    @Before
//    public void ConstructorAndInitialize() {
//        //testBoard = new ChessBoard();
//
//        for (int i=0; i<9; i++) {
//            for (int j=0; j<7; j++) {
//                if (j==3 &&(i==0||i==8))
//                    board.getSquares()[i][j] = new Square(i, j, "穴");
//                else if (((i==0||i==8)&&(j==2||j==4))||(j==3 &&(i==1||i==7)))
//                    board.getSquares()[i][j] = new Square(i, j, "陷");
//                else if ((i<=5&&i>=3)&&(j==1||j==2||j==4||j==5))
//                    board.getSquares()[i][j] = new Square(i, j, "河");
//                else
//                    board.getSquares()[i][j] = new Square(i, j, " ");
//            }
//        }
//
//        //set animal locations
//        rat0 = new Rat(0);
//        rat1 = new Rat(1);
//        dog1 = new Dog(1);
//        lion0 = new Lion(0);
//        elephant0 = new Elephant(0);
//        cat1 = new Cat(1);
//        elephant1 = new Elephant(1);
//        tiger1 = new Tiger(1);
//
//        lion0.setLocation(3,0);
//        rat0.setLocation(4,0);
//        rat1.setLocation(4,2);
//        elephant0.setLocation(1,3);
//        elephant1.setLocation(2,1);
//        dog1.setLocation(2,3);
//        cat1.setLocation(3,3);
//        tiger1.setLocation(0,4);
//
//        board.getSquares()[3][0].setAnimal(lion0);
//        board.getSquares()[4][0].setAnimal(rat0);
//        board.getSquares()[4][2].setAnimal(rat1);
//        board.getSquares()[1][3].setAnimal(elephant0);
//        board.getSquares()[2][1].setAnimal(elephant1);
//        board.getSquares()[2][3].setAnimal(dog1);
//        board.getSquares()[3][3].setAnimal(cat1);
//        board.getSquares()[0][4].setAnimal(tiger1);
//
//    }
//
//    @Test
//    public void moveTest1() {
//        //test move(int direction), i.e. check whether this piece is moved successfully
//        //rat1 originally at (4,2)
//        //rat1 move up is (5,2)
//        assertEquals(4,rat1.getLocation()[0]);
//        assertEquals(2,rat1.getLocation()[1]);
//
//        rat1.move(0);
//        assertEquals(5,rat1.getLocation()[0]);
//        assertEquals(2,rat1.getLocation()[1]);
//
//        //rat1 move left is (5,3)
//        rat1.move(2);
//        assertEquals(5,rat1.getLocation()[0]);
//        assertEquals(3,rat1.getLocation()[1]);
//
//        //can not be out of chess board
//        tiger1.move(1);
//        assertEquals(0,tiger1.getLocation()[0]);
//        assertEquals(4,tiger1.getLocation()[1]);
//
//        rat1.move(3);
//        rat1.move(1);
//    }
//
//    @Test
//    public void checkJumpLegalTest1() {
//        //test checkJumpLegal(Square s), i.e. check whether this piece can jump legally
//
//        //rat1 is at (4,2) on land
//        //lion0 is at (3,0) on land, it can jump to (3,5) and capture cat1, since we use the adjacent location to judge
//        //jump legality, here the destination is (3,1)
//        assertTrue(lion0.checkJumpLegal(board.getSquares()[3][1]));//can't jump because rat in intervening water squares
//
//        rat1.move(1);//move down;
//        //rat0 is now at (2,3) in river
//
//        //lion0 is at (3,0) on land, rat0 is on the way to jump, lion cannot jump. since we use the adjacent location to judge
//        //jump legality, here the destination is (3,1)
//        assertFalse(lion0.checkJumpLegal(board.getSquares()[3][1]));//can jump because rat not in intervening water squares
//        rat1.move(0);//move down
//    }
//
//
//    @Test
//    public void checkSwimLegalTest1() {
//        //test checkSwimLegal(Square s), i.e. check whether this piece can swim legally
//        //rat0 is at (4,2) on land
//
//        rat1.move(3);//move right
//        //rat1 now at (4,1)
//        //The rat may not capture the elephant or another rat on land directly from a water square. Similarly, a rat on land may not attack a rat in the water.
//        //rat0 cannot capture rat1
//        //rat0 cannot swim from (4,2) land to (4,1) river
//        assertFalse(rat0.checkSwimLegal(board.getSquares()[4][1]));
//
//        rat1.move(1);//move down
//
//        //rat can swim
//        //rat0 can move from (4,0) land to (4,1) river
//        assertTrue(rat0.checkSwimLegal(board.getSquares()[4][1]));
//
//
//        //rat1 now at (3,1)
//        //The rat may not capture the elephant or another rat on land directly from a water square. Similarly, a rat on land may not attack a rat in the water.
//        //rat0 cannot capture rat1
//        //rat0 cannot swim from (3,1) land to (2,1) river
//        assertFalse(rat1.checkSwimLegal(board.getSquares()[2][1]));
//
//        rat1.move(0);
//        rat1.move(2);
//    }
//
//    @Test
//    public void checkIsCanCaptureTest1() {
//        //test isCanCapture(Animal a), i.e. check whether the capture will be success
//        //rat0 is originally at (4,0) on land, rat1 is originally at (4,2) in river
//
//        rat0.move(3); //move right
//        rat0.move(0);//move up
//        // now rat0 is at (3,1) in river
//
//        //The rat may not capture the elephant or another rat on land directly from a water square.
//        //rat0 cannot capture elephant1
//        assertFalse(rat0.isCanCapture(elephant1));
//
//        rat1.move(1); //move down
//        // now rat1 is at (3,2) on land
//
//        // both rat0 and rat1 is in river
//        assertTrue(rat0.isCanCapture(rat1));
//
//        rat1.move(0); //move up, move rat 1 back to (4,2)
//
//
//        rat0.move(3);//move right
//        rat0.move(0);//move up
//        //now rat0 is at (2,2) on land
//
//        //both rat0 and elephant1 is on land
//        //rat can capture elephant
//        assertTrue(rat0.isCanCapture(elephant1));
//
//        //lion0 is at (3,0) on land, it can directly jump to (3,3), cat1 is at (3,3)
//        //lion can capture cat
//        assertTrue(lion0.isCanCapture(cat1));
//
//        //cannot capture animal in same side
//        assertFalse(dog1.isCanCapture(cat1));
//
//        //elephant0 is originally at (1,3) in trap, dog1 is originally at (2,3) on land
//        //A piece may capture any enemy piece in one of the player's trap squares regardless of rank.
//        //dog1 can capture elephant0
//        assertTrue(dog1.isCanCapture(elephant0));
//
//        rat0.move(1);
//        rat0.move(2);
//        rat0.move(1);
//        rat0.move(2);
//
//    }
//
//
//    @Test
//    public void checkMoveLegalTest1() {
//        //test checkMoveLegal(Square s), i.e. check this animal is allowed to move to the expected destination square
//
//        //rat0 is originally at (4,0) on land
//        //rat can swim, rat0 can get into the river, legal
//        assertTrue(rat0.checkMoveLegal(board.getSquares()[4][1]));
//
//        //lion0 is originally at (3,0) on land
//        // lion can't swim in river, lino0 cannot get into the river, illegal
//        assertFalse(lion0.checkMoveLegal(board.getSquares()[4][1]));
//
//        //cat1 is at (3,3), dog1 is at (2,3)
//        //cat1 can't move down because dog1 is here, illegal
//        assertFalse(cat1.checkMoveLegal(board.getSquares()[2][3]));//cat1 can't move down because dog1 is here
//
//        //elephant0 is originally at (1,3) in trap, dog1 is originally at (2,3) on land
//        //A piece may capture any enemy piece in one of the player's trap squares regardless of rank.
//        //dog1 can capture elephant0, moving is legal
//        assertTrue(dog1.checkMoveLegal(board.getSquares()[1][3]));
//
//        //elephant rank is higher than dog
//        //elephant0 can capture dog1, moving is legal
//        assertTrue(elephant0.checkMoveLegal(board.getSquares()[2][3]));
//
//        //tiger 1 is originally at (0,4)
//        //A piece may not move to its own den.
//        assertFalse(tiger1.checkMoveLegal(board.getSquares()[0][3]));
//
//    }
//
//}