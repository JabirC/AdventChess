package adventchess.chessgame.logic;
import java.util.ArrayList;
import java.util.List;
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

        // Check if any opponent piece can attack the king's position
        for(ChessPiece opponentPiece: board.getPieces(opponentColor)){
            if(opponentPiece.isValidMove(board, kingPosition[0], kingPosition[1])){
                return true;
            }
        }
        return false;
    }

    public boolean isCheckMated(String color){
        // Player is in check
        if(isChecked(color)){
            // King cannot make a move
            if(!hasLegalKingMoves(color)){
                // No piece can make a move
                if(!hasLegalPieceMoves(color)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isStaleMate(String color){
        // Check if the player is in check
        if(isChecked(color)){
            return false;
        }

        // Check if the player has no legal moves
        if(!hasLegalPieceMoves(color)){
            return true;
        }

        // If neither condition is met, it's not a stalemate
        return false;
    }

    public boolean hasLegalKingMoves(String color){
        ChessPiece king = board.getKing(color);
        int[] currentPosition = king.getCurrentPosition();
        List<int[]> possibleMoves = king.possibleMoves(board);

        // Check if move will lead to a check
        for(int[] move: possibleMoves){
            // Simulate the move
            ChessPiece deleted = board.movePiece(king, move);

            // Check if the king is not in check after the move
            if(!isChecked(color)){
                // Undo the move 
                board.reverseMove(deleted, move, currentPosition);
                return true;
            }

            // Undo the move as it didn't lead to a legal move
            board.reverseMove(deleted, move, currentPosition);
        }
        return false;
    }

    public boolean hasLegalPieceMoves(String color){
        ArrayList<ChessPiece> pieces = board.getPieces(color);
        
        for(ChessPiece piece: pieces){
            int[] currentPosition = piece.getCurrentPosition();
            for(int[] move: piece.possibleMoves(board)){
                // Simulate the move
                ChessPiece deleted = board.movePiece(piece, move);
    
                // Check if the king is not in check after the move
                if(!isChecked(color)){
                    // Undo the move 
                    board.reverseMove(deleted, move, currentPosition);
                    return true;
                }
    
                // Undo the move as it didn't lead to a legal move
                board.reverseMove(deleted, move, currentPosition);
            }
        }
        return false;
    }
}