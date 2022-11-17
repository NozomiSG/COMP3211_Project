package view;

import model.ChessBoard;
import model.Square;


public class monitor {
    public static void printChessboard(ChessBoard board){
        Square[][] currentBoard = board.getSquares();
        System.out.println("==========================");
        for (int i=0; i<9; i++) {
            for (int j=0; j<7; j++) {
                if(currentBoard[i][j].getAnimal()!=null && currentBoard[i][j].getAnimal().getSide()==0){
                    System.out.format("\33[44;97;1m"+currentBoard[i][j].printSquare()+"\033[m");
                }else if(currentBoard[i][j].getAnimal()!=null && currentBoard[i][j].getAnimal().getSide()==1)
                    System.out.format("\33[46;97;1m"+currentBoard[i][j].printSquare()+"\033[m");
                else{
                    System.out.print("\33[47;97m"+currentBoard[i][j].printSquare()+"\033[m");
                }
            }
            System.out.println();
        }
        System.out.println("==========================");
    }

    public static void printWarning(String warn, boolean ifPrint){
        if (ifPrint)
            System.out.println("Warning: " + warn);
    } //print warning message when users try to do invalid action

    public static void printWelcome() {} //print welcome message

    public static void printManual() {} // print help message including move and manual commands

    public static void noticeToMove(int side) {
        if (side == 0)
            System.out.print("Player1 move: ");
        else
            System.out.print("Player2 move: ");
    } // Notification to users in every turn.


    public static void printWinMessage(int side) {
        if (side == 0)
            System.out.println("Player1 win the game!");
        else
            System.out.println("Player2 win the game!");
    } // Print winner

}
