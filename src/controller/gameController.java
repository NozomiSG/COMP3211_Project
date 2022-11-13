package controller;

import model.Animal;
import model.ChessBoard;
import model.Player;
import model.Square;
import view.monitor;

import java.util.Scanner;

import static model.ChessBoard.board;


public class gameController {
    Player current_player = board.getPlayer0();
    int side = 0;


    public static void gameProcess(){
        // Print welcome message
        monitor.printWelcome();
        monitor.printHelp();
        ChessBoard board= new ChessBoard();
        Scanner scanner = new Scanner(System.in);
//        while (checkEndGame()) {
        monitor.printChessboard(board);

//        }

    } // Start the game with a while loop


    public Animal readActionToAnimal(String input){
        Character rankc = input.charAt(0);
        int rank = rankc - 48;
        Animal animal_selected = current_player.getAnimals()[rank];
        return animal_selected;

    }

    // if lion, tiger, opposite river; else move with direction, no check leagal
    public Square readActionToSquare(String input){
        Animal animal = readActionToAnimal(input);
        int rank = animal.getRank();
        int side = current_player.getSide();
        int  ax , ay;
        ax = animal.getLocation()[0];
        ay = animal.getLocation()[1];
        Character direction = input.charAt(1);
        if (rank == 6 || rank ==7){
            if (direction =='w') {
                ax-=1;
                if(board.getSquares()[ax][ay].getType().equals('河')){
                    ax -= 3;
                }
            }
            else if (direction =='s'){
                ax+=1;
                if(board.getSquares()[ax][ay].getType().equals('河')){
                    ax += 3;
                }
            }
            else if (direction =='a'){
                ay-=1;
                if(board.getSquares()[ax][ay].getType().equals('河')){
                    ay -= 2;
                }
            }
            else if (direction =='d'){
                ay+=1;
                if(board.getSquares()[ax][ay].getType().equals('河')){
                    ay += 2;
                }
            }
        }
        else  {
            if (direction =='w') ax-=1;
            else if (direction =='s') ax+=1;
            else if (direction =='a') ay-=1;
            else if (direction =='d') ay+=1;
        }

        return board.getSquares()[ax][ay];
    }

    public static boolean moveAnimal(Animal a, Square s) {
        if(a.checkMoveLegal(s)){
            //clear the animal at the original location
            board.getSquares()[a.getLocation()[0]][a.getLocation()[1]].setAnimal(null);
            //set the new location to animal
            a.setLocation(s.getLocation()[0],s.getLocation()[1]);
            //set animal in new location in chessboard
            board.getSquares()[s.getLocation()[0]][s.getLocation()[1]].setAnimal(a);
            return true;}
        else return false;

    } //move an animal

    public void changeTurn() {
        if (side == 0 ){
            side = 1;
            current_player = board.getPlayer1();
        }
        else {
            side = 0;
            current_player = board.getPlayer0();
        }
    } // change player side

    public static boolean checkWinner() {
        return true;
    } // check if there is a winner

    public static boolean checkRiver(Square s) {
        return (s.getType().equals("河"));
    } //check if it's in river

    public static boolean checkBoundary(Square s) {
        if (s.getLocation()[0]==0||s.getLocation()[0]==8)
            return true;
        if (s.getLocation()[1]==0||s.getLocation()[1]==6)
            return true;
        return false;
    } //check if destination is boundary

    public static boolean checkSelfDens(Player p,Square s) {
        if(p.getSide()==0)
            if(s.getLocation()[0]==8 && s.getLocation()[1]==3)
                return true;
        if(p.getSide()==1)
            if(s.getLocation()[0]==0 && s.getLocation()[1]==3)
                return true;
        return false;
    } //check if it's a den of a player himself

    public static boolean checkEndGame() {
        boolean flag = true;
        return flag;
    } //check if the game ends




}
