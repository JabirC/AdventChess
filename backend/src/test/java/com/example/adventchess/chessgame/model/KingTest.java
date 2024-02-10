import adventchess.chessgame.model.*;
import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class KingTest {

    // @Test
    // public void testPossibleMovesWhiteKing() {
    //     // Create a king at position (3, 3)
    //     King king = new King("White", 3, 3);

    //     // Test possible moves
    //     List<int[]> moves = king.possibleMoves();

    //     // Assert that the list of moves contains the expected moves
    //     assertTrue(ChessBoard.containsMove(moves, new int[]{2, 2})); // Diagonal up-left
    //     assertTrue(ChessBoard.containsMove(moves, new int[]{2, 3})); // Up
    //     assertTrue(ChessBoard.containsMove(moves, new int[]{2, 4})); // Diagonal up-right
    //     assertTrue(ChessBoard.containsMove(moves, new int[]{3, 2})); // Left
    //     assertTrue(ChessBoard.containsMove(moves, new int[]{3, 4})); // Right
    //     assertTrue(ChessBoard.containsMove(moves, new int[]{4, 2})); // Diagonal down-left
    //     assertTrue(ChessBoard.containsMove(moves, new int[]{4, 3})); // Down
    //     assertTrue(ChessBoard.containsMove(moves, new int[]{4, 4})); // Diagonal down-right
    //     assertFalse(ChessBoard.containsMove(moves, new int[]{3, 3})); // Current position
    //     assertEquals(8, moves.size()); // Ensure no unexpected moves
    // }


    // @Test
    // public void testPossibleMovesBlackKing() {
    //     // Create a king at position (3, 3)
    //     King king = new King("Black", 3, 3);

    //     // Test possible moves
    //     List<int[]> moves = king.possibleMoves();

    //     // Assert that the list of moves contains the expected moves
    //     assertTrue(ChessBoard.containsMove(moves, new int[]{2, 2})); // Diagonal up-left
    //     assertTrue(ChessBoard.containsMove(moves, new int[]{2, 3})); // Up
    //     assertTrue(ChessBoard.containsMove(moves, new int[]{2, 4})); // Diagonal up-right
    //     assertTrue(ChessBoard.containsMove(moves, new int[]{3, 2})); // Left
    //     assertTrue(ChessBoard.containsMove(moves, new int[]{3, 4})); // Right
    //     assertTrue(ChessBoard.containsMove(moves, new int[]{4, 2})); // Diagonal down-left
    //     assertTrue(ChessBoard.containsMove(moves, new int[]{4, 3})); // Down
    //     assertTrue(ChessBoard.containsMove(moves, new int[]{4, 4})); // Diagonal down-right
    //     assertFalse(ChessBoard.containsMove(moves, new int[]{3, 3})); // Current position
    //     assertEquals(8, moves.size()); // Ensure no unexpected moves
    // }

}