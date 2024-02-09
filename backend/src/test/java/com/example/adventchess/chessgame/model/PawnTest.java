import adventchess.chessgame.model.*;
import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {


    
    @Test
    public void testPossibleMovesWhitePawn() {
        // Create a white pawn at the initial position
        Pawn whitePawn = new Pawn("White",1, 3);

        // Test possible moves
        List<int[]> moves = whitePawn.possibleMoves();
        
        // Assert that the list of moves contains the expected moves
        assertTrue(containsMove(moves, new int[]{2, 3})); // Forward one square
        assertTrue(containsMove(moves, new int[]{3, 3})); // Forward two squares (initial move)
        assertTrue(containsMove(moves, new int[]{2, 4})); // Diagonal capture
        assertTrue(containsMove(moves, new int[]{2, 2})); // Diagonal capture
        assertEquals(4, moves.size()); // Ensure no unexpected moves
    }

    @Test
    public void testPossibleMovesBlackPawn() {
        // Create a black pawn at a non-initial position
        Pawn blackPawn = new Pawn("Black",6, 6);
        blackPawn.setCurrentPosition(4,6);

        // Test possible moves
        List<int[]> moves = blackPawn.possibleMoves();

        assertTrue(containsMove(moves, new int[]{3, 6})); // Forward one square
        assertFalse(containsMove(moves, new int[]{2, 6})); // Cannot move two squares (not initial move)
        assertTrue(containsMove(moves, new int[]{3, 7})); // Diagonal capture
        assertTrue(containsMove(moves, new int[]{3, 5})); // Diagonal capture
        assertEquals(3, moves.size()); // Ensure no unexpected moves
    }

    @Test
    public void testPossibleMovesEdgeCases() {
        // Test edge cases, such as pawn at the board's edge
        Pawn whitePawnAtEdge = new Pawn("White", 1, 0);
        whitePawnAtEdge.setCurrentPosition(7,0);
        List<int[]> moves = whitePawnAtEdge.possibleMoves();
        for(int[] line: moves){
            System.out.println(line[0]);
            System.out.println(line[1]);
        }
        assertTrue(moves.isEmpty()); // Pawn at the edge cannot move forward

        // Test with a pawn in a non-standard initial position
        Pawn blackPawnNonStandard = new Pawn("Black", 6, 3);
        blackPawnNonStandard.setCurrentPosition(5,3);
        moves = blackPawnNonStandard.possibleMoves();

        assertFalse(containsMove(moves,new int[]{6, 3})); 
        assertFalse(containsMove(moves,new int[]{6, 4})); 
        assertTrue(containsMove(moves,new int[]{4, 3})); 
        assertTrue(containsMove(moves,new int[]{4, 4}));
        assertTrue(containsMove(moves,new int[]{4, 2})); 
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


    @Test
    public void testValidMoveWhitePawn() {
        ChessBoard board = new ChessBoard();
        ChessPiece[][] chessBoard = board.getBoard();
    }


}