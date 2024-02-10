import adventchess.chessgame.model.*;
import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KnightTest {

    @Test
    void testPossibleMovesKnight() {
        // Set up
        ChessBoard board = new ChessBoard();
        ChessPiece knight = board.getPieceAt(0,1);

        // Rook can move horizontally and vertically
        List<int[]> knightMoves = knight.possibleMoves(board);
        assertEquals(2, knightMoves.size());

        // Check horizontal moves
        assertTrue(containsMove(knightMoves, new int[]{2, 0}));
        assertTrue(containsMove(knightMoves, new int[]{2, 2}));
    }

    @Test
    void testPossibleMovesKnightBlocked() {
        // Set up
        ChessBoard board = new ChessBoard();
        Knight knight = new Knight("White", 3, 4);
        board.placePiece(knight, 3, 4);

        ChessPiece whitePawn1 = board.getPieceAt(1, 2);
        board.placePiece(whitePawn1, 2, 2);

        ChessPiece whitePawn2 = board.getPieceAt(1, 6);
        board.placePiece(whitePawn2, 2, 6);

        ChessPiece blackPawn1 = board.getPieceAt(6, 2);
        board.placePiece(blackPawn1, 4, 2);

        ChessPiece blackPawn2 = board.getPieceAt(6, 6);
        board.placePiece(blackPawn2, 4, 6);

        ChessPiece blackPawn3 = board.getPieceAt(6, 3);
        board.placePiece(blackPawn3, 5, 3);

        ChessPiece blackPawn4 = board.getPieceAt(6, 5);
        board.placePiece(blackPawn4, 5, 5);

        // 4 possible moves: can eat 4 black pawns
        List<int[]> knightMoves = knight.possibleMoves(board);
        assertEquals(4, knightMoves.size());


        assertTrue(containsMove(knightMoves, new int[]{4, 2}));
        assertTrue(containsMove(knightMoves, new int[]{4, 6}));
        assertTrue(containsMove(knightMoves, new int[]{5, 3}));
        assertTrue(containsMove(knightMoves, new int[]{5, 5}));
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