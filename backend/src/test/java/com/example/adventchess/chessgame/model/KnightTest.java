import adventchess.chessgame.model.*;
import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KnightTest {

    @Test
    public void testPossibleMovesWhiteKnight() {
        // Create a knight at position (3, 3)
        Knight knight = new Knight("White", 3, 3);

        // Test possible moves
        List<int[]> moves = knight.possibleMoves();

        // Assert that the list of moves contains the expected moves
        assertTrue(containsMove(moves, new int[]{1, 2})); // Up-left
        assertTrue(containsMove(moves, new int[]{1, 4})); // Up-right
        assertTrue(containsMove(moves, new int[]{2, 1})); // Left-up
        assertTrue(containsMove(moves, new int[]{2, 5})); // Right-up
        assertTrue(containsMove(moves, new int[]{4, 1})); // Left-down
        assertTrue(containsMove(moves, new int[]{4, 5})); // Right-down
        assertTrue(containsMove(moves, new int[]{5, 2})); // Down-left
        assertTrue(containsMove(moves, new int[]{5, 4})); // Down-right
        assertFalse(containsMove(moves, new int[]{3, 3})); // Current position
        assertEquals(8, moves.size()); // Ensure no unexpected moves
    }

    @Test
    public void testPossibleMovesBlackKnight() {
        // Create a knight at position (3, 3)
        Knight knight = new Knight("Black", 7, 1);

        // Test possible moves
        List<int[]> moves = knight.possibleMoves();

        // Assert that the list of moves contains the expected moves
        assertTrue(containsMove(moves, new int[]{6, 3})); // Up-left
        assertTrue(containsMove(moves, new int[]{5, 2})); // Up-right
        assertTrue(containsMove(moves, new int[]{5, 0})); // Left-up
        assertFalse(containsMove(moves, new int[]{7, 1})); // Current position
        assertEquals(3, moves.size()); // Ensure no unexpected moves
    }

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