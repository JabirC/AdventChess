package adventchess.chessgame.model;
import java.util.ArrayList;
import java.util.List;
// Queen
public class Queen extends ChessPiece{
    public Queen(String color, int homeRow, int homeColumn) {
        super("Queen", color, homeRow, homeColumn);
    }

    public List<int[]> possibleMoves() {
        int[] position = getCurrentPosition();
        int currentRow = position[0];
        int currentColumn = position[1];

        List<int[]> moves = new ArrayList<>();


        // Check moves along the rank (horizontal)
        for (int i = 0; i < 8; i++) {
            if (i != currentColumn) {
                moves.add(new int[]{currentRow, i});
            }
        }

        // Check moves along the file (vertical)
        for (int i = 0; i < 8; i++) {
            if (i != currentRow) {
                moves.add(new int[]{i, currentColumn});
            }
        }

        // Check moves diagonally
        for (int i = 1; i < 8; i++) {
            addMove(moves, currentRow + i, currentColumn + i); // Diagonal down-right
            addMove(moves, currentRow - i, currentColumn - i); // Diagonal up-left
            addMove(moves, currentRow + i, currentColumn - i); // Diagonal down-left
            addMove(moves, currentRow - i, currentColumn + i); // Diagonal up-right
        }
        return moves;
    }

    private void addMove(List<int[]> moves, int newRow, int newCol) {
        if (isValidPosition(newRow, newCol)) {
            moves.add(new int[]{newRow, newCol});
        }
    }

    public boolean isValidMove(ChessBoard board, int toRow, int toCol){
        List<int[]> moves = possibleMoves();  
        return true;
    }
}