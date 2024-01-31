package src.engine.logic;

import com.chess.adventchess.model.ChessBoard;

public class ChessGame {
    private ChessBoard board;

    public ChessGame() {
        this.board = new ChessBoard();
    }

    public void makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (MoveValidator.isValidMove(board, fromRow, fromCol, toRow, toCol)) {
            // Update the game state based on the move
            // ...
        } else {
            // Handle invalid move
            // ...
        }
    }

    // Additional methods for game state, game outcome, etc.
}