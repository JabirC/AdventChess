import adventchess.chessgame.model.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChessBoardTest {

    @Test
    public void testChessBoardInitialization() {
        ChessBoard chessBoard = new ChessBoard();
        ChessPiece[][] board = chessBoard.getBoard();

        // Check that the white pieces are in their starting positions
        assertTrue(board[0][0] instanceof Rook);
        assertTrue(board[0][1] instanceof Knight);
        assertTrue(board[0][2] instanceof Bishop);
        assertTrue(board[0][3] instanceof Queen);
        assertTrue(board[0][4] instanceof King);
        assertTrue(board[0][5] instanceof Bishop);
        assertTrue(board[0][6] instanceof Knight);
        assertTrue(board[0][7] instanceof Rook);

        for (int i = 0; i < 8; i++) {
            assertTrue(board[1][i] instanceof Pawn);
        }

        // Check that the black pieces are in their starting positions
        assertTrue(board[7][0] instanceof Rook);
        assertTrue(board[7][1] instanceof Knight);
        assertTrue(board[7][2] instanceof Bishop);
        assertTrue(board[7][3] instanceof Queen);
        assertTrue(board[7][4] instanceof King);
        assertTrue(board[7][5] instanceof Bishop);
        assertTrue(board[7][6] instanceof Knight);
        assertTrue(board[7][7] instanceof Rook);

        for (int i = 0; i < 8; i++) {
            assertTrue(board[6][i] instanceof Pawn);
        }
    }

    @Test
    public void testIsValidPosition() {
        // public static boolean isValidPosition(int row, int column)

        // Test valid positions
        assertTrue(ChessBoard.isValidPosition(0, 0));  // Top-left corner
        assertTrue(ChessBoard.isValidPosition(0, 0));  // Top-left corner
        assertTrue(ChessBoard.isValidPosition(7, 7));  // Bottom-right corner
        assertTrue(ChessBoard.isValidPosition(3, 5));  // Middle of the board

        // Test invalid positions
        assertFalse(ChessBoard.isValidPosition(-1, 2));  // Row below the board
        assertFalse(ChessBoard.isValidPosition(8, 4));   // Row above the board
        assertFalse(ChessBoard.isValidPosition(2, -3));  // Column left of the board
        assertFalse(ChessBoard.isValidPosition(6, 8));   // Column right of the board

    }

}