package adventchess.chessgame.logic;

import adventchess.chessgame.model.*;

public class ChessGame {
    private ChessBoard board;
    private Player playerWhite;
    private Player playerBlack;

    public ChessGame(String playerWhiteName, String playerBlackName) {
        this.board = new ChessBoard();
        playerWhite = new Player("White", playerWhiteName);
        playerBlack = new Player("Black", playerBlackName);
    }

    public ChessGame(String playerWhiteName, String playerBlackName, String[][] initialState) {
        this.board = new ChessBoard(initialState);
        playerWhite = new Player("White", playerWhiteName);
        playerBlack = new Player("Black", playerBlackName);
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

    public boolean isChecked(String color){
        ChessPiece king = board.getKing(color);
        int[] kingPosition = king.getCurrentPosition();
        String opponentColor = color.equals("White") ? "Black" : "White";

        // Check if opponent pieces contain the king's position as a valid move
        for(ChessPiece opponentPiece: board.getPieces(opponentColor)){
            if(opponentPiece.isValidMove(board, kingPosition[0], kingPosition[1])){
                return true;
            }
        }
        return false;
    }
    // Additional methods for game state, game outcome, etc.
}