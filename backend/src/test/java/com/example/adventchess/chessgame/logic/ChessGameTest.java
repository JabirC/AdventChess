import adventchess.chessgame.model.*;
import adventchess.chessgame.logic.*;
import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChessGameTest {

    @Test
    public void testIsCheck() {
        String[][] whiteInCheckState = {
            {"BR", "--", "--", "BK", "--", "--", "--", "--"},
            {"BP", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "BP", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "--", "--", "--", "--", "--"},
            {"WR", "--", "--", "--", "--", "BQ", "--", "WK"}
        };
        ChessGame gameInCheck = new ChessGame("Alice", "Bob", whiteInCheckState);
        assertTrue(gameInCheck.isChecked("White"));
        assertFalse(gameInCheck.isChecked("Black"));
    }

    @Test
    public void testIsCheck2() {
        String[][] whiteNotInCheckState = {
            {"BR", "--", "--", "BK", "--", "--", "--", "--"},
            {"BP", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "BP", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "--", "--", "--", "--", "--"},
            {"WR", "--", "--", "--", "--", "BQ", "WR", "WK"}
        };
        ChessGame gameInCheck = new ChessGame("Alice", "Bob", whiteNotInCheckState);
        assertFalse(gameInCheck.isChecked("White"));
        assertFalse(gameInCheck.isChecked("Black"));
    }

    @Test
    public void testIsCheck3() {
        String[][] whiteInCheckState = {
            {"BR", "--", "--", "BK", "--", "--", "--", "--"},
            {"BP", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "BP", "BB", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "--", "--", "--", "--", "--"},
            {"WR", "--", "--", "--", "--", "BQ", "WR", "WK"}
        };
        ChessGame gameInCheck = new ChessGame("Alice", "Bob", whiteInCheckState);
        assertTrue(gameInCheck.isChecked("White"));
        assertFalse(gameInCheck.isChecked("Black"));
    }


    @Test
    public void testIsCheck4() {
        String[][] whiteNotInCheckState = {
            {"BR", "BN", "BB", "BQ", "BK", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "--", "--", "BP", "BP"},
            {"--", "--", "--", "--", "--", "BP", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "--", "--"},
            {"--", "--", "--", "--", "WP", "--", "--", "--"},
            {"--", "--", "--", "WP", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "--", "--", "WP", "WP", "WP"},
            {"WR", "WN", "WB", "WQ", "WK", "WB", "WN", "WR"}
        };
        ChessGame gameInCheck = new ChessGame("Alice", "Bob", whiteNotInCheckState);
        assertFalse(gameInCheck.isChecked("White"));
        assertFalse(gameInCheck.isChecked("Black"));
    }

    @Test
    public void testIsCheck5() {
        String[][] blackInCheckState = {
            {"BR", "BN", "BB", "BQ", "BK", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "--", "--", "BP", "BP"},
            {"--", "--", "--", "--", "--", "BP", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "--", "WQ"},
            {"--", "--", "--", "--", "WP", "--", "--", "--"},
            {"--", "--", "--", "WP", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "--", "--", "WP", "WP", "WP"},
            {"WR", "WN", "WB", "--", "WK", "WB", "WN", "WR"}
        };
        ChessGame gameInCheck = new ChessGame("Alice", "Bob", blackInCheckState);
        assertFalse(gameInCheck.isChecked("White"));
        assertTrue(gameInCheck.isChecked("Black"));
    }

    @Test
    public void testIsCheck6() {
        String[][] blackNotInCheckState = {
            {"BR", "BN", "BB", "BQ", "--", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "BK", "--", "BP", "BP"},
            {"--", "--", "--", "--", "--", "BP", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "--", "WQ"},
            {"--", "--", "--", "--", "WP", "--", "--", "--"},
            {"--", "--", "--", "WP", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "--", "--", "WP", "WP", "WP"},
            {"WR", "WN", "WB", "--", "WK", "WB", "WN", "WR"}
        };
        ChessGame gameInCheck = new ChessGame("Alice", "Bob", blackNotInCheckState);
        assertFalse(gameInCheck.isChecked("White"));
        assertFalse(gameInCheck.isChecked("Black"));
    }

    @Test
    public void testIsCheck7() {
        String[][] blackInCheckState = {
            {"BR", "BN", "BB", "BQ", "--", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "BK", "--", "BP", "BP"},
            {"--", "--", "--", "--", "--", "BP", "--", "--"},
            {"--", "--", "--", "WN", "BP", "--", "--", "WQ"},
            {"--", "--", "--", "--", "WP", "--", "--", "--"},
            {"--", "--", "--", "WP", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "--", "--", "WP", "WP", "WP"},
            {"WR", "--", "WB", "--", "WK", "WB", "WN", "WR"}
        };
        ChessGame gameInCheck = new ChessGame("Alice", "Bob", blackInCheckState);
        assertFalse(gameInCheck.isChecked("White"));
        assertTrue(gameInCheck.isChecked("Black"));
    }

    @Test
    public void testIsCheck8() {
        String[][] blackInCheckState = {
            {"BR", "BN", "BB", "BQ", "--", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "--", "BK", "BP", "BP"},
            {"--", "--", "--", "--", "--", "BP", "--", "--"},
            {"--", "--", "--", "WN", "BP", "--", "--", "WQ"},
            {"--", "--", "--", "--", "WP", "--", "--", "--"},
            {"--", "--", "--", "WP", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "--", "--", "WP", "WP", "WP"},
            {"WR", "--", "WB", "--", "WK", "WB", "WN", "WR"}
        };
        ChessGame gameInCheck = new ChessGame("Alice", "Bob", blackInCheckState);
        assertFalse(gameInCheck.isChecked("White"));
        assertTrue(gameInCheck.isChecked("Black"));
    }


    @Test
    public void testIsCheck9() {
        String[][] whiteInCheckState = {
            {"--", "--", "BR", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "WK", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "BP", "--"},
            {"--", "--", "--", "--", "--", "--", "WR", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame gameInCheck = new ChessGame("Alice", "Bob", whiteInCheckState);
        assertFalse(gameInCheck.isChecked("White"));
    }

    @Test
    public void testIsCheck10() {
        String[][] whiteInCheckState = {
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "WK", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "BP", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "BK", "--"}
        };
        ChessGame gameInCheck = new ChessGame("Alice", "Bob", whiteInCheckState);
        assertFalse(gameInCheck.isChecked("White"));
    }

    @Test
    public void testKingHasLegalMoves() {
        String[][] board = {
            {"BR", "BN", "BB", "BQ", "--", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "--", "BK", "BP", "BP"},
            {"--", "--", "--", "--", "--", "BP", "--", "--"},
            {"--", "--", "--", "WN", "BP", "WP", "--", "WQ"},
            {"--", "--", "--", "--", "WP", "--", "--", "--"},
            {"--", "--", "--", "WP", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "--", "--", "--", "WP", "WP"},
            {"WR", "--", "WB", "--", "WK", "WB", "WN", "WR"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertFalse(game.hasLegalKingMoves("Black"));
    }

    @Test
    public void testKingHasLegalMoves2() {
        String[][] board = {
            {"BR", "BN", "BB", "BQ", "--", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "--", "BK", "BP", "BP"},
            {"--", "--", "--", "--", "--", "BP", "--", "--"},
            {"--", "--", "--", "WN", "BP", "--", "--", "WQ"},
            {"--", "--", "--", "--", "WP", "--", "--", "--"},
            {"--", "--", "--", "WP", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "--", "--", "WP", "WP", "WP"},
            {"WR", "--", "WB", "--", "WK", "WB", "WN", "WR"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertTrue(game.hasLegalKingMoves("Black"));
    }

    @Test
    public void testKingHasLegalMoves3() {
        String[][] board = {
            {"--", "--", "BR", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "BP", "WR", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertTrue(game.hasLegalKingMoves("White"));
    }

    @Test
    public void testKingHasLegalMoves4() {
        String[][] board = {
            {"--", "--", "BR", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "BP", "--"},
            {"--", "--", "--", "--", "--", "--", "WR", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertTrue(game.hasLegalKingMoves("White"));
    }

    @Test
    public void testKingHasLegalMoves5() {
        String[][] board = {
            {"--", "--", "BR", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "BB", "--"},
            {"--", "--", "--", "--", "--", "--", "WR", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertFalse(game.hasLegalKingMoves("White"));
    }

    @Test
    public void testCanAnyPieceInterfere() {
        String[][] board = {
            {"--", "--", "BR", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "BB", "--"},
            {"--", "--", "--", "--", "--", "--", "WR", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertFalse(game.hasLegalPieceMoves("White"));
    }

    @Test
    public void testCanAnyPieceInterfere2() {
        String[][] board = {
            {"--", "--", "BR", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "BN", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "WR", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertTrue(game.hasLegalPieceMoves("White"));
    }

    @Test
    public void testCanAnyPieceInterfere3() {
        String[][] board = {
            {"--", "--", "BR", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "BN", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "WN", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertFalse(game.hasLegalPieceMoves("White"));
    }

    @Test
    public void testCanAnyPieceInterfere4() {
        String[][] board = {
            {"--", "--", "BR", "--", "WK", "--", "--", "--"},
            {"--", "WP", "--", "--", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "BN", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "WN", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertTrue(game.hasLegalPieceMoves("White"));
    }

    @Test
    public void testCanAnyPieceInterfere5() {
        String[][] board = {
            {"--", "BR", "--", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "WP", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "BN", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "WN", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertTrue(game.hasLegalPieceMoves("White"));
    }


    @Test
    public void testCanAnyPieceInterfere6() {
        String[][] board = {
            {"--", "BR", "--", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "--", "--"},
            {"WB", "--", "--", "--", "--", "--", "BN", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "WN", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertTrue(game.hasLegalPieceMoves("White"));
    }

    @Test
    public void testCanAnyPieceInterfere7() {
        String[][] board = {
            {"--", "BR", "--", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "BP", "--", "BK", "--", "--", "--", "--"},
            {"WB", "--", "--", "--", "--", "--", "BN", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "WN", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertFalse(game.hasLegalPieceMoves("White"));
    }

    @Test
    public void testCanAnyPieceInterfere8() {
        String[][] board = {
            {"--", "BR", "--", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "BP", "--", "BK", "WN", "--", "--", "--"},
            {"WB", "--", "--", "--", "--", "--", "BN", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertTrue(game.hasLegalPieceMoves("White"));
    }


    @Test
    public void testIsCheckMated() {
        String[][] board = {
            {"--", "--", "BR", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "BB", "--"},
            {"--", "--", "--", "--", "--", "--", "WR", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertTrue(game.isCheckMated("White"));
    }

    @Test
    public void testIsCheckMated2() {
        String[][] board = {
            {"--", "--", "BR", "--", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "BP", "--", "WP", "--"},
            {"--", "--", "--", "BK", "--", "--", "BP", "--"},
            {"--", "--", "--", "--", "--", "--", "WR", "--"},
            {"--", "--", "--", "WN", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertFalse(game.isCheckMated("White"));
    }

    @Test
    public void testIsCheckMated3() {
        String[][] board = {
            {"WR", "--", "--", "--", "--", "BK", "--", "--"},
            {"--", "WR", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "WK", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertTrue(game.isCheckMated("Black"));
    }


    @Test
    public void testIsCheckMated4() {
        String[][] board = {
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"BK", "WQ", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "WK", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertTrue(game.isCheckMated("Black"));
    }


    @Test
    public void testIsCheckMated5() {
        String[][] board = {
            {"--", "BK", "WQ", "--", "--", "--", "--", "--"},
            {"--", "BP", "--", "--", "--", "--", "--", "--"},
            {"--", "WK", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertFalse(game.isCheckMated("Black"));
    }


    @Test
    public void testIsCheckMated6() {
        String[][] board = {
            {"--", "--", "--", "WR", "--", "--", "BK", "--"},
            {"--", "--", "--", "--", "--", "BP", "BP", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "BP"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "BN", "WB", "--", "--", "--", "WP"},
            {"--", "BR", "--", "--", "--", "WP", "WP", "--"},
            {"--", "--", "--", "--", "--", "--", "WK", "--"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertTrue(game.isCheckMated("Black"));
    }

    @Test
    public void testIsCheckMated7() {
        String[][] board = {
            {"--", "--", "--", "--", "BK", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "WQ", "WK", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertFalse(game.isCheckMated("Black"));
    }

    @Test
    public void testIsCheckMated8() {
        String[][] board = {
            {"--", "--", "--", "--", "WN", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "WB", "--", "--"},
            {"--", "--", "--", "WB", "BK", "--", "--", "--"},
            {"--", "--", "--", "--", "WP", "--", "--", "--"},
            {"--", "--", "--", "--", "WP", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "WK", "--"}
        };
        ChessGame game = new ChessGame("Alice", "Bob", board);
        assertTrue(game.isCheckMated("Black"));
    }

}