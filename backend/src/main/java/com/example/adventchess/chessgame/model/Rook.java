package adventchess.chessgame.model;
import java.util.ArrayList;
import java.util.List;
// Rook
public class Rook extends ChessPiece{
    public Rook(String color, int homeRow, int homeColumn) {
        super("Rook", color, homeRow, homeColumn);
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
        return moves;
    }

    public boolean isValidMove(ChessBoard board, int toRow, int toCol){
        List<int[]> moves = possibleMoves();  
        return true;
    }
}