import adventchess.chessgame.model.*;
import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RookTest {

    // @Test
    // public void testPossibleMovesWhiteRook() {
    //     // Create a rook at position (3, 3)
    //     Rook rook = new Rook("White", 3, 3);

    //     // Test possible moves
    //     List<int[]> moves = rook.possibleMoves();

    //     // Assert that the list of moves contains the expected moves
    //     assertTrue(containsMove(moves, new int[]{0, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{1, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{2, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{4, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{5, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{6, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{7, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{3, 0})); // Along the rank (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 1})); // Along the rank (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 2})); // Along the rank (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 4})); // Along the rank (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 5})); // Along the rank (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 6})); // Along the rank (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 7})); // Along the rank (horizontal)
    //     assertEquals(14, moves.size()); // Ensure no unexpected moves
    // }

    // @Test
    // public void testPossibleMovesBlackRook() {
    //     // Create a rook at position (3, 3)
    //     Rook rook = new Rook("Black", 3, 3);

    //     // Test possible moves
    //     List<int[]> moves = rook.possibleMoves();

    //     // Assert that the list of moves contains the expected moves
    //     assertTrue(containsMove(moves, new int[]{0, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{1, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{2, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{4, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{5, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{6, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{7, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{3, 0})); // Along the rank (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 1})); // Along the rank (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 2})); // Along the rank (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 4})); // Along the rank (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 5})); // Along the rank (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 6})); // Along the rank (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 7})); // Along the rank (horizontal)
    //     assertEquals(14, moves.size()); // Ensure no unexpected moves
    // }

    // Helper method to check if a specific move is present in the list
    private boolean containsMove(List<int[]> moves, int[] targetMove) {
        for (int[] move : moves) {
            if (Arrays.equals(move, targetMove)) {
                return true;
            }
        }
        return false;
    }


}