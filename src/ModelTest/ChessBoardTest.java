package ModelTest;

import model.ChessBoard;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class ChessBoardTest {
    ChessBoard testBoard;

    @Before
    public void chessBoardConstructor() {testBoard = new ChessBoard();}

    @Test
    public void getSquaresTest1() {
        //check chessboard size i.e. horizontal and vertical square number
        assertEquals(9, testBoard.getSquares().length);
        assertEquals(7, testBoard.getSquares()[0].length);
    }

    @Test
    public void getSquaresTest2() {
        //test the initialization of the chessboard by using getSquare(), i.e. check the square type
        assertEquals("陷", testBoard.getSquares()[0][2].getType());
        assertEquals("河", testBoard.getSquares()[5][1].getType());
        assertEquals("穴", testBoard.getSquares()[8][3].getType());
        assertEquals("　", testBoard.getSquares()[0][0].getType());
    }

    @Test
    public void getPlayer0Test() {
        // Test: Return the side result of player0 from getSide()
        assertEquals(0, testBoard.getPlayer0().getSide());
    }

    @Test
    public void getPlayer1Test() {
        // Test: Return the side result of player1 from getSide()
        assertEquals(1, testBoard.getPlayer1().getSide());
    }




}