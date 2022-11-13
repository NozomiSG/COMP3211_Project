import controller.gameController;
import view.monitor;

import static model.ChessBoard.board;

public class Main {
    // Main function for this game
    public static void main(String[] args) {



        gameController.gameProcess();
        System.out.print("--------------------------");
//        board.getPlayer1().getAnimals()[0].move(0);
        monitor.printChessboard(board);
//        board.getSquares()[6][6].setAnimal(null);
//        board.getPlayer1().getAnimals()[0].setLocation(6,5);
//        board.getSquares()[6][5].setAnimal(board.getPlayer1().getAnimals()[0]);
//        System.out.println(board.getPlayer1().getAnimals()[0].checkSwimLegal(board.getSquares()[5][5]));
//
//        board.getSquares()[0][0].setAnimal(null);
//        board.getPlayer1().getAnimals()[7].setLocation(2,1);
//        board.getSquares()[2][1].setAnimal(board.getPlayer1().getAnimals()[7]);
//
//        board.getSquares()[2][0].setAnimal(null);
//        board.getPlayer1().getAnimals()[0].setLocation(5,1);
//        board.getSquares()[5][1].setAnimal(board.getPlayer1().getAnimals()[0]);
//        System.out.println(board.getPlayer1().getAnimals()[7].checkJumpLegal(board.getSquares()[3][1]));






    }

}

