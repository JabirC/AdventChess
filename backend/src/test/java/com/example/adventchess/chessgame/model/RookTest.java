import adventchess.chessgame.model.*;
import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RookTest {

    @Test
    void testPossibleMovesRookHorizontalVertical() {
        // Set up
        ChessBoard board = new ChessBoard();
        Rook rook = new Rook("White", 3, 3);
        board.placePiece(rook,3,3);

        // Rook can move horizontally and vertically
        List<int[]> rookMoves = rook.possibleMoves(board);
        assertEquals(11, rookMoves.size());

        // Check horizontal moves
        assertTrue(containsMove(rookMoves, new int[]{3, 0}));
        assertTrue(containsMove(rookMoves, new int[]{3, 1}));
        assertTrue(containsMove(rookMoves, new int[]{3, 2}));
        assertTrue(containsMove(rookMoves, new int[]{3, 4}));
        assertTrue(containsMove(rookMoves, new int[]{3, 5}));
        assertTrue(containsMove(rookMoves, new int[]{3, 6}));
        assertTrue(containsMove(rookMoves, new int[]{3, 7}));

        // Check vertical moves
        assertFalse(containsMove(rookMoves, new int[]{1, 3}));
        assertTrue(containsMove(rookMoves, new int[]{2, 3}));
        assertTrue(containsMove(rookMoves, new int[]{4, 3}));
        assertTrue(containsMove(rookMoves, new int[]{5, 3}));
        assertTrue(containsMove(rookMoves, new int[]{6, 3}));
    }


    @Test
    void testPossibleMovesRookBlocked() {
        // Set up
        ChessBoard board = new ChessBoard();
        ChessPiece rook = board.getPieceAt(0,0);
        board.placePiece(rook,3,4);

        ChessPiece whiteknight1 = board.getPieceAt(0,1);
        board.placePiece(whiteknight1,3,3);

        ChessPiece whiteknight2 = board.getPieceAt(0,6);
        board.placePiece(whiteknight2,2,4);

        ChessPiece blackknight1 = board.getPieceAt(7,1);
        board.placePiece(blackknight1,4,4);

        ChessPiece blackknight2 = board.getPieceAt(7,6);
        board.placePiece(blackknight2,3,5);

        // Rook can move horizontally and vertically
        List<int[]> rookMoves = rook.possibleMoves(board);
        printListIntArrays(rookMoves);
        assertEquals(2, rookMoves.size());

        assertTrue(containsMove(rookMoves, new int[]{4, 4}));
        assertTrue(containsMove(rookMoves, new int[]{3, 5}));
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

    private static void printListIntArrays(List<int[]> list) {
        for (int[] array : list) {
            printIntArray(array);
        }
    }

    private static void printIntArray(int[] array) {
        System.out.print("[ ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" ]");
    }


}