package view;

import model.ChessBoard;
import model.Square;


public class monitor {
    public static void printChessboard(ChessBoard board){
        Square[][] currentBoard = board.getSquares();
        System.out.println();
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
        System.out.println(" ____                _ ");
        System.out.println("| __ ) _   _  ____  | |");
        System.out.println("|  _ \\| | | |/ __ \\ | |");
        System.out.println("| |_) | |_| |  ___/ |_|");
        System.out.println("|____/ \\__, |\\___\\  (_)");
        System.out.println("       |___/        ");
    }

    public static void printWelcome() {
        System.out.print("__        __   _   \n\\ \\      / /__| | ___ ___  _ __ ___   ___ \n \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\\n  \\ V  V /  __/ | (_| (_) | | | | | |  __/\n   \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|\n");
        System.out.println("==========================================");
        System.out.println("Welcome to Jungle game!");
    } //print welcome message

    public static void printManual() {
        System.out.printf("%s%s%s\n","This is a user manual. Enter \"","\33[;;1m"+"help"+"\033[m","\" if you want to read it again.");
        System.out.println("Instructions:");
        System.out.printf("  %s%s\n","\33[;;1m"+"[number][direction]"+"\033[m"," -move certain pieces in given direction");
        System.out.println("  [e.g. “1s”-rat go down one step]");
        System.out.printf("  %-5s\t%-10s\t%-10s\n","Rank","Piece(en)","Piece(cn)");
        System.out.printf("  %-5d\t%-10s\t%-10s\n",8,"Elephant","象");
        System.out.printf("  %-5d\t%-10s\t%-10s\t%s\n",7,"Lion","獅","Direction: w-UP|a-LEFT|s-DOWN|d-RIGHT");
        System.out.printf("  %-5d\t%-10s\t%-10s\t%s%s%s\n",6,"Tiger","虎","Input \"","\33[;;1m"+"exit"+"\033[m","\" -quit the game");
        System.out.printf("  %-5d\t%-10s\t%-10s\t%s%s%s\n",5,"Leopard","豹","Input \"","\33[;;1m"+"help"+"\033[m","\" -print the user manual");
        System.out.printf("  %-5d\t%-10s\t%-10s\t%s%s%s\n",4,"Wolf","狼","Input \"","\33[;;1m"+"restart"+"\033[m","\" -restart the game");
        System.out.printf("  %-5d\t%-10s\t%-10s\t%s\n",3,"Dog","狗","河: River");
        System.out.printf("  %-5d\t%-10s\t%-10s\t%s\n",2,"Cat","貓","陷: Trap");
        System.out.printf("  %-5d\t%-10s\t%-10s\t%s\n",1,"Rat","鼠","穴: Den");
        System.out.println();
//        System.out.println("\tdirection: w-UP|a-LEFT|s-DOWN|d-RIGHT");
//        System.out.println("\texit -quit the game");
//        System.out.println("\thelp -print the user manual");
//        System.out.println("\trestart -restart the game");
    } // print help message including move and manual commands

    public static void noticeToMove(int side) {
        if (side == 0)
            System.out.print("\33[;31;1m"+"Player1 move: "+"\033[m");
        else
            System.out.print("\33[;34;1m"+"Player2 move: "+"\033[m");
    } // Notification to users in every turn.


    public static void printWinMessage(int side) {
        if (side == 0)
            System.out.println("\33[;31;1m"+"Player1 win the game!"+"\033[m");
        else
            System.out.println("\33[;34;1m"+"Player2 win the game!"+"\033[m");
    } // Print winner

}
