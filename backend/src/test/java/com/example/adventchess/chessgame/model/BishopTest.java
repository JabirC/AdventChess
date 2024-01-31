import adventchess.chessgame.model.*;
import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BishopTest {

    @Test
    public void testPossibleMovesWhiteBishopWhiteSquare() {
        Bishop bishop = new Bishop("White", 3, 2);

        // Test possible moves
        List<int[]> moves = bishop.possibleMoves();

        // Assert that the list of moves contains the expected moves
        assertTrue(containsMove(moves, new int[]{5, 0})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{4, 1})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{2, 3})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{1, 4})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{0, 5})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{1, 0})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{2, 1})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{4, 3})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{5, 4})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{6, 5})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{7, 6})); // Along the file (Diagonal)
        assertFalse(containsMove(moves, new int[]{5, 1})); // Along the file (Diagonal)
        assertEquals(11, moves.size()); // Ensure no unexpected moves
    }
    

    @Test
    public void testPossibleMovesBlackBishopWhiteSquare() {
        Bishop bishop = new Bishop("Black", 3, 2);

        // Test possible moves
        List<int[]> moves = bishop.possibleMoves();

        // Assert that the list of moves contains the expected moves
        assertTrue(containsMove(moves, new int[]{5, 0})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{4, 1})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{2, 3})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{1, 4})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{0, 5})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{1, 0})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{2, 1})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{4, 3})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{5, 4})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{6, 5})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{7, 6})); // Along the file (Diagonal)
        assertFalse(containsMove(moves, new int[]{5, 1})); // Along the file (Diagonal)
        assertEquals(11, moves.size()); // Ensure no unexpected moves
    }

    @Test
    public void testPossibleMovesWhiteBishopBlackSquare() {
        Bishop bishop = new Bishop("White", 3, 5);

        // Test possible moves
        List<int[]> moves = bishop.possibleMoves();

        // Assert that the list of moves contains the expected moves
        assertTrue(containsMove(moves, new int[]{0, 2})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{1, 3})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{2, 4})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{4, 6})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{5, 7})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{1, 7})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{2, 6})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{4, 4})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{5, 3})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{6, 2})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{7, 1})); // Along the file (Diagonal)
        assertFalse(containsMove(moves, new int[]{4, 5})); // Along the file (Diagonal)
        assertEquals(11, moves.size()); // Ensure no unexpected moves
    }
    

    @Test
    public void testPossibleMovesBlackBishopBlackSquare() {
        Bishop bishop = new Bishop("Black", 3, 5);

        // Test possible moves
        List<int[]> moves = bishop.possibleMoves();

        // Assert that the list of moves contains the expected moves
        assertTrue(containsMove(moves, new int[]{0, 2})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{1, 3})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{2, 4})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{4, 6})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{5, 7})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{1, 7})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{2, 6})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{4, 4})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{5, 3})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{6, 2})); // Along the file (Diagonal)
        assertTrue(containsMove(moves, new int[]{7, 1})); // Along the file (Diagonal)
        assertFalse(containsMove(moves, new int[]{4, 5})); // Along the file (Diagonal)
        assertEquals(11, moves.size()); // Ensure no unexpected moves
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