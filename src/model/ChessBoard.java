package model;

public class ChessBoard {
    // animal in two camp
    public static ChessBoard board = new ChessBoard();
    private static Player player0;
    private static Player player1;
    // Init every square in chessboard
    private static Square[][] chessboard;
    public ChessBoard() {
        initChessBoard();
    }

    public void initChessBoard() {
        chessboard = new Square[9][7];
        player0 = new Player(0);
        player1 = new Player(1);
        //chessboard = new Square[9][7];
        for (int i=0; i<9; i++) {
            for (int j=0; j<7; j++) {
                if (j==3 &&(i==0||i==8))
                    chessboard[i][j] = new Square(i, j, "穴");
                else if (((i==0||i==8)&&(j==2||j==4))||(j==3 &&(i==1||i==7)))
                    chessboard[i][j] = new Square(i, j, "陷");
                else if ((i<=5&&i>=3)&&(j==1||j==2||j==4||j==5))
                    chessboard[i][j] = new Square(i, j, "河");
                else
                    chessboard[i][j] = new Square(i, j, "　");
            }
        }
        for (Animal animal1 : player0.getAnimals())
            chessboard[animal1.getLocation()[0]][animal1.getLocation()[1]].setAnimal(animal1);
        for (Animal animal2 : player1.getAnimals())
            chessboard[animal2.getLocation()[0]][animal2.getLocation()[1]].setAnimal(animal2);
    }

    public Square[][] getSquares() {
        return chessboard;
    }

    public Square getSquareByAnimal(Animal a){
        int x=a.getLocation()[0];
        int y=a.getLocation()[1];
        return this.getSquares()[x][y];
    }

    public Player getPlayer0() {
        return player0;
    }

    public Player getPlayer1() {
        return player1;
    }
}
