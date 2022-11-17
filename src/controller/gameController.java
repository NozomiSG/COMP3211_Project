package controller;

import model.Animal;
import model.Player;
import model.Square;
import view.monitor;


import java.util.Scanner;


import static model.ChessBoard.board;


public class gameController {
    static Player current_player = board.getPlayer0();
    static int side = 0;


    public static void gameProcess() {
        // Print welcome message
        monitor.printWelcome();
        monitor.printManual();
        Scanner scanner = new Scanner(System.in);
        monitor.printChessboard(board);
        String command;
        while (true) {
            monitor.noticeToMove(side);
            command = scanner.next();
            while(! commandLegal(command)){
                monitor.printWarning("Unknown command, please input again!", true);
                monitor.noticeToMove(side);
                command = scanner.next();
            }
            if (command.equals("exit")){
                monitor.printBye();
                return;
            }
            else if (command.equals("help")){
                monitor.printManual();
                monitor.printChessboard(board);
            }
            else if (command.equals("restart")){
                board.initChessBoard();
                gameController.current_player = board.getPlayer0();
                side = 0;
                gameProcess();
            }
            else{
                Animal selected_animal = readActionToAnimal(command);
                Square dest = readActionToSquare(command);
                boolean legal = selected_animal.move(dest);
                while(!legal){
                    //monitor.printWarning();
                    monitor.noticeToMove(side);
                    command = scanner.next();
                    selected_animal = readActionToAnimal(command);
                    dest = readActionToSquare(command);
                    legal = selected_animal.move(dest);
                }
                if (checkWinner()){
                    monitor.printWinMessage(side);
                    monitor.printBye();
                    return;
                }
                changeTurn();
                monitor.printChessboard(board);
//                    monitor.printTurnChange();
            }
        }
    } // Start the game with a while loop


    public static Animal readActionToAnimal(String input) {
        int rank = input.charAt(0) - 49;
        return current_player.getAnimals()[rank];
    }

    // if lion, tiger, opposite river; else move with direction, no check legal
    public static Square readActionToSquare(String input) {
        Animal animal = readActionToAnimal(input);
        int rank = animal.getRank();
        int ax, ay;
        ax = animal.getLocation()[0];
        ay = animal.getLocation()[1];
        char direction = input.charAt(1);
        if (rank == 6 || rank == 7) {
            if (direction == 'w') {
                ax -= 1;
                if(ax>-1 && ax< 9){
                    if (board.getSquares()[ax][ay].getType().equals("河")) {
                        ax -= 3;
                    }
                }
                else ax +=1;
            } else if (direction == 's') {
                ax += 1;
                if(ax>-1 && ax< 9){
                    if (board.getSquares()[ax][ay].getType().equals("河"))
                        ax += 3;
                }
                else ax-=1;
            } else if (direction == 'a') {
                ay -= 1;
                if(ay>-1 && ay< 7){
                    if (board.getSquares()[ax][ay].getType().equals("河"))
                        ay -= 2;
                }
                else ay +=1;
            } else if (direction == 'd') {
                ay += 1;
                if(ay>-1 && ay< 7){
                    if (board.getSquares()[ax][ay].getType().equals("河"))
                        ay += 2;
                }
                else ay -=1;
            }
        } else {
            if (direction == 'w') {
                if (ax != 0)
                    ax -= 1;
            }
            else if (direction == 's') {
                if (ax != 8)
                    ax += 1;
            }
            else if (direction == 'a') {
                if (ay != 0)
                    ay -= 1;
            }
            else if (direction == 'd') {
                if (ay != 6)
                    ay += 1;
            }
        }
        return board.getSquares()[ax][ay];
    }


    public static void changeTurn() {
        if (side == 0) {
            side = 1;
            current_player = board.getPlayer1();
        } else {
            side = 0;
            current_player = board.getPlayer0();
        }
    } // change player side

    public static boolean checkWinner() {
        int flag = 0;
        for (int i=0; i<8;i++){
            if (side ==0){
                if(board.getPlayer1().getAnimals()[i].getAlive()) flag =1;
            }
            else{
                if(board.getPlayer0().getAnimals()[i].getAlive()) flag =1;
            }
        }
        if(flag == 0) {
//          No enemy animal alive
            return true;
        }
        if (board.getSquares()[8][3].getAnimal()!= null || board.getSquares()[0][3].getAnimal()!= null){
//          Enemy den broken
            return true;
        }
//      Enemy can't move
        else return !checkEnemyCanMove(side);
    } // check if there is a winner

    public static boolean checkEnemyCanMove(int mySide) {
        String direction = "wsad";
        Animal[] animals;
        String input;
        if (mySide == 0) {
            animals = board.getPlayer1().getAnimals();
        } else animals = board.getPlayer0().getAnimals();
        for (int i = 0; i < 8; i++) {
            input = String.valueOf(i+1);
            if (animals[i].getAlive()) {
                for (int j = 0; j < 4; j++) {
                    input = input + direction.charAt(j);
                    Square s = readActionToSquare(input);
                    if(animals[i].checkMoveLegal(s, false)) return true;
                }
            }
        }
        return false;
    }

    public static boolean commandLegal(String input){
        if(input.length() == 4){
            return input.equals("help") || input.equals("exit");
        }
        else if(input.length() == 7){
            return input.equals("restart");
        }
        else if(input.length() == 2){
            if(input.charAt(0)-48<1 || input.charAt(0)-48>8) return false;
            for (char c: "wasd".toCharArray()){
                if (c == input.charAt(1)) {
                    return true;
                }
            }
        }
        return false;
    }
}
