package adventchess.chessgame.model;
import java.util.ArrayList;
import java.util.List;
// King
public class King extends ChessPiece{
    public King(String color, int homeRow, int homeColumn) {
        super("King", color, homeRow, homeColumn);
    }

    public List<int[]> possibleMoves() {
        int[] position = getCurrentPosition();
        int currentRow = position[0];
        int currentColumn = position[1];

        // Depending on the pawn's color, it can move forward one square

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

    private void addMove(List<int[]> moves, int newRow, int newCol) {
        if (isValidPosition(newRow, newCol)) {
            moves.add(new int[]{newRow, newCol});
        }
    }
}