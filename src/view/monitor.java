package view;

import model.ChessBoard;
import model.Square;


public class monitor {
    public static void printChessboard(ChessBoard board){
        Square[][] currentBoard = board.getSquares();
        System.out.println();
        for (int i=0; i<9; i++) {
            for (int j=0; j<7; j++) {
                System.out.print(currentBoard[i][j].printSquare());
            }
            System.out.println();
        }
    }

    public static void printWarning(){
        System.out.println();
    } //print warning message when users try to do invalid action

    public static void printWelcome() {} //print welcome message

    public static void printHelp() {} // print help message including move and manual commands

    public static void printNotice() {} // Notification to users in every turn.

    public static void printTurnChange() {} // Print change turn notice

    public static void printWinMessage() {} // Print winner

}
