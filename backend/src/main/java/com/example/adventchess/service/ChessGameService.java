package com.example.adventchess.service;

import adventchess.chessgame.logic.ChessGame;
import com.example.adventchess.model.GameStateMessage;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;


@Service
public class ChessGameService {

    private final Map<String, ChessGame> userGameMap = new HashMap<>();

    @Autowired
    private final SimpMessageSendingOperations messagingTemplate;

    public ChessGameService(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void createGameSession(String session1, String session2, String mode) {
        String gameId = UUID.randomUUID().toString();
        ChessGame chessGame;
        String[][] adventureBoard = {
            {"BR", "BN", "BB", "BQ", "BK", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "BP", "BP", "BP", "BP"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "WP", "WP", "WP", "WP", "WP"},
            {"WR", "WN", "WB", "WQ", "WK", "WB", "WN", "WR"}
        };

        if(mode.equals("classic")){
            chessGame = new ChessGame(session1, session2);
        }
        else {
            adventureBoard = ChessGame.createRandom();

            // for (int i = 0; i < adventureBoard.length; i++) {
            //     for (int j = 0; j < adventureBoard[i].length; j++) {
            //         System.out.print(adventureBoard[i][j] + " ");
            //     }
            //     System.out.println();
            // }

            chessGame = new ChessGame(session1, session2, adventureBoard);
        }

        // Store the ChessGame instance associated with each user's session ID
        userGameMap.put(session1, chessGame);
        userGameMap.put(session2, chessGame);

        // Send a message to each user to notify the start of the game

        Gson gson = new Gson();
        String message = gson.toJson(new GameStateMessage(gameId, adventureBoard));
        // String message = String.format("{\"gameId\" : \"%s\"}", gameId);

        messagingTemplate.convertAndSend("/topic/reply" + session1, message);
        messagingTemplate.convertAndSend("/topic/reply" + session2, message);
    }

    public void verifyMove(String session, String gameId, String move){
        ChessGame game = userGameMap.get(session);
        messagingTemplate.convertAndSend("/topic/state" + gameId, move);
    }

    public ChessGame getGameBySession(String sessionId) {
        return userGameMap.get(sessionId);
    }

    // Additional methods for managing game state, moves, etc.
}