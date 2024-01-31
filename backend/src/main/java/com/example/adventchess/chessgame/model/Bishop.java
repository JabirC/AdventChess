package adventchess.chessgame.model;
import java.util.ArrayList;
import java.util.List;
// Bishop
public class Bishop extends ChessPiece{
    public Bishop(String color, int homeRow, int homeColumn) {
        super("Bishop", color, homeRow, homeColumn);
    }

    public List<int[]> possibleMoves() {
        int[] position = getCurrentPosition();
        int currentRow = position[0];
        int currentColumn = position[1];


        List<int[]> moves = new ArrayList<>();

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
    
}