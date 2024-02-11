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
}