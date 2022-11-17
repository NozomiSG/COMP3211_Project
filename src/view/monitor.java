package view;

import model.ChessBoard;
import model.Square;


public class monitor {
    public static void printChessboard(ChessBoard board){
        Square[][] currentBoard = board.getSquares();
        System.out.println("============================");
        for (int i=0; i<9; i++) {
            for (int j=0; j<7; j++) {
                if(currentBoard[i][j].getAnimal()!=null && currentBoard[i][j].getAnimal().getSide()==0){
                    System.out.format("\33[41;97;1m"+currentBoard[i][j].printSquare()+"\033[m");
                }else if(currentBoard[i][j].getAnimal()!=null && currentBoard[i][j].getAnimal().getSide()==1)
                    System.out.format("\33[44;97;1m"+currentBoard[i][j].printSquare()+"\033[m");
                else{
                    System.out.format(currentBoard[i][j].printSquare());
                }
            }
            System.out.println();
        }
        System.out.println("============================");
        System.out.println();
    }

    public static void printWarning(String warn, boolean ifPrint){
        if (ifPrint)
            System.out.println("Warning: " + warn);
    } //print warning message when users try to do invalid action

    public static void printBye() {
        System.out.println(" ____             _ ");
        System.out.println("| __ ) _   _  ___| |");
        System.out.println("|  _ \\| | | |/ _ \\ |");
        System.out.println("| |_) | |_| |  __/_|");
        System.out.println("|____/ \\__, |\\___(_)");
        System.out.println("       |___/        ");
    }

    public static void printWelcome() {
        System.out.print("__        __   _   \n\\ \\      / /__| | ___ ___  _ __ ___   ___ \n \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\\n  \\ V  V /  __/ | (_| (_) | | | | | |  __/\n   \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|\n");
        System.out.println("==========================");
        System.out.println("Welcome to Jungle game!");
    } //print welcome message

    public static void printManual() {
        System.out.println();
        System.out.println("This is a user manual. Enter \"help\" if you want to read it again.");
        System.out.println();
        System.out.println("instructions:");
        System.out.println();
        System.out.println("\t[number][direction] -move certain pieces in given direction");
        System.out.println();
        System.out.println("\t\tnumber: 1-鼠|2-貓|3-狗|4-狼|5-豹|6-虎|7-獅|8-象");
        System.out.println();
        System.out.println("\t\tdirection: w-UP|a-LEFT|s-DOWN|d-RIGHT");
        System.out.println();
        System.out.println("\t\t[e.g. “1s”-rat go down one step]");
        System.out.println();
        System.out.println("\texit -quit the game");
        System.out.println();
        System.out.println("\thelp -print the user manual");
        System.out.println();
        System.out.println("\trestart -restart the game");
        System.out.println();
    } // print help message including move and manual commands

    public static void noticeToMove(int side) {
        if (side == 0)
            System.out.print("\33[;31;1m"+"Player1 move: "+"\033[m");
        else
            System.out.print("\33[;34;1m"+"Player2 move: "+"\033[m");
    } // Notification to users in every turn.


    public static void printWinMessage(int side) {
        if (side == 0)
            System.out.println("Player1 win the game!");
        else
            System.out.println("Player2 win the game!");
    } // Print winner

}
