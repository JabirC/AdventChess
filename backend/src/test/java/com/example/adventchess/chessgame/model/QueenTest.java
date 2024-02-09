import adventchess.chessgame.model.*;
import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QueenTest {

    // @Test
    // public void testPossibleMovesWhiteQueen() {
    //     // Create a queen at position (3, 3)
    //     Queen queen = new Queen("White", 3, 3);

    //     // Test possible moves
    //     List<int[]> moves = queen.possibleMoves();

    //     // Assert that the list of moves contains the expected moves
    //     assertTrue(containsMove(moves, new int[]{0, 0})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{1, 1})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{2, 2})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{4, 4})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{5, 5})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{6, 6})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{7, 7})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{2, 4})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{1, 5})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{0, 6})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{4, 2})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{5, 1})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{6, 0})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{3, 0})); // Along the file (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 1})); // Along the file (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 2})); // Along the file (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 4})); // Along the file (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 5})); // Along the file (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 6})); // Along the file (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 7})); // Along the file (horizontal)
    //     assertTrue(containsMove(moves, new int[]{0, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{1, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{2, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{4, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{5, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{6, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{7, 3})); // Along the file (vertical)
    //     assertFalse(containsMove(moves, new int[]{7, 5})); // Not on any valid path
    //     assertEquals(27, moves.size()); // Ensure no unexpected moves
    // }
    

    // @Test
    // public void testPossibleMovesBlackQueen() {
    //     // Create a queen at position (3, 3)
    //     Queen queen = new Queen("Black",3, 3);

    //     // Test possible moves
    //     List<int[]> moves = queen.possibleMoves();

    //     // Assert that the list of moves contains the expected moves
    //     assertTrue(containsMove(moves, new int[]{0, 0})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{1, 1})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{2, 2})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{4, 4})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{5, 5})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{6, 6})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{7, 7})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{2, 4})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{1, 5})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{0, 6})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{4, 2})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{5, 1})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{6, 0})); // Along the file (Diagonal)
    //     assertTrue(containsMove(moves, new int[]{3, 0})); // Along the file (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 1})); // Along the file (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 2})); // Along the file (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 4})); // Along the file (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 5})); // Along the file (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 6})); // Along the file (horizontal)
    //     assertTrue(containsMove(moves, new int[]{3, 7})); // Along the file (horizontal)
    //     assertTrue(containsMove(moves, new int[]{0, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{1, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{2, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{4, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{5, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{6, 3})); // Along the file (vertical)
    //     assertTrue(containsMove(moves, new int[]{7, 3})); // Along the file (vertical)
    //     assertFalse(containsMove(moves, new int[]{7, 5})); // Not on any valid path
    //     assertEquals(27, moves.size()); // Ensure no unexpected moves
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