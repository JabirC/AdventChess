package adventchess.chessgame.model;

public class ChessBoard {
    private ChessPiece[][] board;

    public ChessBoard() {
        // Initialize the chess board
        this.board = new ChessPiece[8][8];
        initializeBoard();
    }

    private void initializeBoard() {
        // Place white pieces
        placePiece(new Rook("White", 0, 0), 0, 0);
        placePiece(new Knight("White", 0, 1), 0, 1);
        placePiece(new Bishop("White", 0, 2), 0, 2);
        placePiece(new Queen("White", 0, 3), 0, 3);
        placePiece(new King("White", 0, 4), 0, 4);
        placePiece(new Bishop("White", 0, 5), 0, 5);
        placePiece(new Knight("White", 0, 6), 0, 6);
        placePiece(new Rook("White", 0, 7), 0, 7);

        for (int i = 0; i < 8; i++) {
            placePiece(new Pawn("White", 1, i), 1, i);
        }

        // Place black pieces
        placePiece(new Rook("Black", 7, 0), 7, 0);
        placePiece(new Knight("Black", 7, 1), 7, 1);
        placePiece(new Bishop("Black", 7, 2), 7, 2);
        placePiece(new Queen("Black", 7, 3), 7, 3);
        placePiece(new King("Black", 7, 4), 7, 4);
        placePiece(new Bishop("Black", 7, 5), 7, 5);
        placePiece(new Knight("Black", 7, 6), 7, 6);
        placePiece(new Rook("Black", 7, 7), 7, 7);

        for (int i = 0; i < 8; i++) {
            placePiece(new Pawn("Black", 6, i), 6, i);
        }
    }

    // Method to place a chess piece on the board
    public void placePiece(ChessPiece piece, int row, int column) {
        // Check if the position is within the board boundaries
        if (isValidPosition(row, column)) {
            // Check if the square is empty before placing the piece
            if (board[row][column] == null) {
                board[row][column] = piece;
                // Update the piece's current position
                piece.setCurrentPosition(row, column);
            } else {
                // Handle error or throw an exception (square is occupied)
                System.out.println("Error: Square is already occupied");
            }
        } else {
            // Handle error or throw an exception (invalid position)
            System.out.println("Error: Invalid position");
        }
    }

    // Method to check if a position is within the board boundaries
    public static boolean isValidPosition(int row, int column) {
        return row >= 0 && row < 8 && column >= 0 && column < 8;
    }


    // Getter method to retrieve the current state of the board
    public ChessPiece[][] getBoard() {
        return board;
    }
}