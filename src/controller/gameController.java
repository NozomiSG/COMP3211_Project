package controller;

import model.Animal;
import model.Animals.Cat;
import model.ChessBoard;
import model.Player;
import model.Square;
import view.monitor;

import java.awt.*;
import java.util.Scanner;


import static model.ChessBoard.board;


public class gameController {
    static Player current_player = board.getPlayer0();
    static int side = 0;


    public static void gameProcess() {
        // Print welcome message
        monitor.printWelcome();
//        monitor.printHelp();

        Scanner scanner = new Scanner(System.in);
        boolean end = false;
//        monitor.printHelp();
        monitor.printChessboard(board);
        String command;
        while (true) {
            System.out.print("Please input command with out blanks, end with enter:");
            command = scanner.next();
            while(! commandLegal(command)){
                System.out.print("command illegal, please input again: ");
                command = scanner.next();
            }
            if(command.equals("end")){
                break;
            }
            if(command.equals("help")){
//                monitor.printHelp();
                continue;
            }
            else{
                Animal selected_animal = readActionToAnimal(command);
                Square dest = readActionToSquare(command);
                boolean legal = selected_animal.move(dest);
                while(!legal){
                    //monitor.printWarning();
                    System.out.print("move illegal, please input again: ");//change to warning
                    command = scanner.next();
                    selected_animal = readActionToAnimal(command);
                    dest = readActionToSquare(command);
                    legal = selected_animal.move(dest);
                }
                if(legal){
                    if(checkWinner()){
//                        monitor.printWinMessage();
                        break;
                    }
                    changeTurn();
                    monitor.printChessboard(board);
//                    monitor.printTurnChange();
                    System.out.println(side);

                }

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
        int side = current_player.getSide();
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
                    if (board.getSquares()[ax][ay].getType().equals("河")) {
                        ax += 3;
                    }
                }
                else ax-=1;


            } else if (direction == 'a') {
                ay -= 1;
                if(ay>-1 && ay< 7){
                    if (board.getSquares()[ax][ay].getType().equals("河")) {
                        ay -= 2;
                    }
                }
                else ay +=1;

            } else if (direction == 'd') {
                ay += 1;
                if(ay>-1 && ay< 7){
                    if (board.getSquares()[ax][ay].getType().equals("河")) {
                        ay += 2;
                    }
                }
                else ay -=1;

            }
        }
        else {
            if (direction == 'w') ax -= 1;
            else if (direction == 's') ax += 1;
            else if (direction == 'a') ay -= 1;
            else if (direction == 'd') ay += 1;
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
            System.out.println("no enemy animal alive");
            return true;
        }
        if (board.getSquares()[8][3].getAnimal()!= null || board.getSquares()[0][3].getAnimal()!= null){
            System.out.println("enemy den broken");
            return true;
        }
        else if(!checkEnemyCanMove(side)){
            System.out.println("enemy can't move");
            return true;
        }

        return false;
    } // check if there is a winner

    public static boolean checkRiver(Square s) {
        return (s.getType().equals("河"));
    } //check if it's in river

//    public static boolean checkBoundary(Square s) {
//        if (s.getLocation()[0] == 0 || s.getLocation()[0] == 8)
//            return true;
//        if (s.getLocation()[1] == 0 || s.getLocation()[1] == 6)
//            return true;
//        return false;
//    } //check if destination is boundary
//
//    public static boolean checkSelfDens(Player p, Square s) {
//        if (p.getSide() == 0)
//            if (s.getLocation()[0] == 8 && s.getLocation()[1] == 3)
//                return true;
//        if (p.getSide() == 1)
//            if (s.getLocation()[0] == 0 && s.getLocation()[1] == 3)
//                return true;
//        return false;
//    } //check if it's a den of a player himself

//    public static boolean checkEndGame() {
//        boolean flag = true;
//        return flag;
//    } //check if the game ends

    public static boolean checkEnemyCanMove(int mySide) {
        String direction = "wsad";
        Animal[] animals;
        boolean flag = false;
        String input;
        if (mySide == 0) {
            animals = board.getPlayer1().getAnimals();
        } else animals = board.getPlayer0().getAnimals();

        for (int i = 0; i < 8; i++) {
            input = String.valueOf(i+1);
            if (animals[i].getAlive()) {
                for (int j = 0; j < 4; j++) {
                    input = input +String.valueOf(direction.charAt(j));
                    Square s = readActionToSquare(input);
                    System.out.println(i);
                    System.out.print(s.getLocation()[0]);
                    System.out.print(s.getLocation()[1]);
                    System.out.print("\n");
                    if(animals[i].checkMoveLegal(s)){

                        return true;
                    }
                }
            }
        }
        return false;


    }

    public static boolean commandLegal(String input){
        if(!(input.length()==2 || input.length()==3 || input.length()==4 )) return false;
        if(input.length() == 3){
            if (!input.equals("end")) return false;
        }
        else if(input.length() == 4){
            if (!input.equals("help")) return false;
        }
        else{
            boolean flag = false;
            if(input.charAt(0)-48<1 || input.charAt(0)-48>8) return false;
            for (char c: "wasd".toCharArray()){
                if(c == input.charAt(1)) flag = true;
            }
            return flag;
        }
        return true;
    }
}