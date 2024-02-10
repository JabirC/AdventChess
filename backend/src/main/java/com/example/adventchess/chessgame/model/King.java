package adventchess.chessgame.model;
import java.util.ArrayList;
import java.util.List;
// King
public class King extends ChessPiece{
    public King(String color, int homeRow, int homeColumn) {
        super("King", color, homeRow, homeColumn);
    }

    public List<int[]> possibleMoves(ChessBoard board) {
        int[] position = getCurrentPosition();
        int currentRow = position[0];
        int currentColumn = position[1];

        List<int[]> moves = new ArrayList<>();


        // Check moves horizontally, vertically, and diagonally
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // Exclude the position of the king itself
                if (i != 0 || j != 0) {
                    addMove(moves, currentRow + i, currentColumn + j);
                }
            }
        }
        return moves;
    }

    public boolean isValidMove(ChessBoard board, int toRow, int toCol){
        // List<int[]> moves = possibleMoves();  
        return true;
    }
}