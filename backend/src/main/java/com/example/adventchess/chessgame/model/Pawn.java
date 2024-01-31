package adventchess.chessgame.model;
import java.util.ArrayList;
import java.util.List;

// Pawn
public class Pawn extends ChessPiece{
    public Pawn(String color, int homeRow, int homeColumn) {
        super("Pawn", color, homeRow, homeColumn);
    }

    public List<int[]> possibleMoves() {
        int[] position = getCurrentPosition();
        int currentRow = position[0];
        int currentColumn = position[1];

        // Depending on the pawn's color, it can move forward one square
        int forwardDirection = getColor().equals("White") ? 1 : -1;

        List<int[]> moves = new ArrayList<>();

        if (isValidPosition(currentRow + forwardDirection, currentColumn)) {
            moves.add(new int[]{currentRow + forwardDirection, currentColumn});
        }

        // Optionally, a pawn can move two squares forward on its first move
        if (currentRow == getHome()[0]) {
            moves.add(new int[]{currentRow + 2 * forwardDirection, currentColumn});
        }

        if(isValidPosition(currentRow + forwardDirection, currentColumn + forwardDirection)){
            moves.add(new int[]{currentRow + forwardDirection, currentColumn + forwardDirection});
        }

        if(isValidPosition(currentRow + forwardDirection, currentColumn - forwardDirection)){
            moves.add(new int[]{currentRow + forwardDirection, currentColumn - forwardDirection});
        }
        return moves;
    }
}