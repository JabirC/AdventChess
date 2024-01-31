package adventchess.chessgame.model;
import java.util.ArrayList;
import java.util.List;

public abstract class ChessPiece {
    private String name;
    private String color;
    private int homeRow;
    private int homeColumn;
    private int curRow;
    private int curColumn;

    public ChessPiece(String name, String color, int homeRow, int homeColumn){
        this.name = name;
        this.color = color;
        this.homeRow = homeRow;
        this.homeColumn = homeColumn;
        this.curRow = homeRow;
        this.curColumn = homeColumn;
    }

    // Getter methods

    public String getName(){
        return name;
    }

    public String getColor(){
        return color;
    }

    public int[] getHome(){
        int[] home = {homeRow, homeColumn};
        return home;
    }

    public int[] getCurrentPosition(){
        int [] cur = {curRow, curColumn};
        return cur;
    }

    // Setter method
    public void setCurrentPosition(int row, int column){
        this.curRow = row;
        this.curColumn = column;
    }


    // Abstract method for possible moves
    public abstract List<int[]> possibleMoves();


    // Method to check if a position is within the board boundaries
    public static boolean isValidPosition(int row, int column) {
        return row >= 0 && row < 8 && column >= 0 && column < 8;
    }
}