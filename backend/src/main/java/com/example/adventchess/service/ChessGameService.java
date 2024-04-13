package com.example.adventchess.service;

import adventchess.chessgame.logic.ChessGame;
import com.example.adventchess.model.GameStateMessage;
import com.example.adventchess.model.MoveMessage;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Random;

import com.google.gson.Gson;


@Service
public class ChessGameService {

    private final Map<String, ChessGame> userGameMap = new HashMap<>();
    private final Map<String, String> rematchMap = new HashMap<>();

    @Autowired
    private final SimpMessageSendingOperations messagingTemplate;

    public ChessGameService(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void createGameSession(String session1, String session2, String mode) {
        String gameId = UUID.randomUUID().toString();
        ChessGame chessGame;
        String[][] board = {
            {"BR", "BN", "BB", "BQ", "BK", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "BP", "BP", "BP", "BP"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "WP", "WP", "WP", "WP", "WP"},
            {"WR", "WN", "WB", "WQ", "WK", "WB", "WN", "WR"}
        };
        Random random = new Random();
        Boolean isFirstSessionWhite = random.nextBoolean();

        if(mode.equals("classic")){
            if(isFirstSessionWhite){
                chessGame = new ChessGame(gameId, session1, session2);
            }
            else{
                chessGame = new ChessGame(gameId, session2, session1);
            }
        }
        else {
            board = ChessGame.createRandom();
            if(isFirstSessionWhite){
                chessGame = new ChessGame(gameId, session1, session2, board);
            }
            else{
                chessGame = new ChessGame(gameId, session2, session1, board);
            }
        }

        // Store the ChessGame instance associated with each user's session ID
        userGameMap.put(session1, chessGame);
        userGameMap.put(session2, chessGame);

        // Send a message to each user to notify the start of the game

        Gson gson = new Gson();
        String messageSession1 = gson.toJson(new GameStateMessage(gameId, chessGame.getStringBoard(), isFirstSessionWhite, isFirstSessionWhite, new MoveMessage(-1, -1, -1, -1)));
        String messageSession2 = gson.toJson(new GameStateMessage(gameId, chessGame.getStringBoard(), !isFirstSessionWhite, !isFirstSessionWhite, new MoveMessage(-1, -1, -1, -1)));
        // String message = String.format("{\"gameId\" : \"%s\"}", gameId);

        messagingTemplate.convertAndSend("/topic/reply" + session1, messageSession1);
        messagingTemplate.convertAndSend("/topic/reply" + session2, messageSession2);
    }


    public void startRematch(String gameId, String session1, String mode) {
        String session2 = rematchMap.get(gameId);
        rematchMap.remove(gameId);
        ChessGame chessGame;
        String[][] board = {
            {"BR", "BN", "BB", "BQ", "BK", "BB", "BN", "BR"},
            {"BP", "BP", "BP", "BP", "BP", "BP", "BP", "BP"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"--", "--", "--", "--", "--", "--", "--", "--"},
            {"WP", "WP", "WP", "WP", "WP", "WP", "WP", "WP"},
            {"WR", "WN", "WB", "WQ", "WK", "WB", "WN", "WR"}
        };
        Random random = new Random();
        Boolean isFirstSessionWhite = random.nextBoolean();

        if(mode.equals("classic")){
            if(isFirstSessionWhite){
                chessGame = new ChessGame(gameId, session1, session2);
            }
            else{
                chessGame = new ChessGame(gameId, session2, session1);
            }
        }
        else {
            board = ChessGame.createRandom();
            if(isFirstSessionWhite){
                chessGame = new ChessGame(gameId, session1, session2, board);
            }
            else{
                chessGame = new ChessGame(gameId, session2, session1, board);
            }
        }

        // Store the ChessGame instance associated with each user's session ID
        userGameMap.put(session1, chessGame);
        userGameMap.put(session2, chessGame);

        // Send a message to each user to notify the start of the game

        Gson gson = new Gson();
        String messageSession1 = gson.toJson(new GameStateMessage(gameId, chessGame.getStringBoard(), isFirstSessionWhite, isFirstSessionWhite, new MoveMessage(-1, -1, -1, -1)));
        String messageSession2 = gson.toJson(new GameStateMessage(gameId, chessGame.getStringBoard(), !isFirstSessionWhite, !isFirstSessionWhite, new MoveMessage(-1, -1, -1, -1)));
        // String message = String.format("{\"gameId\" : \"%s\"}", gameId);

        messagingTemplate.convertAndSend("/topic/reply" + session1, messageSession1);
        messagingTemplate.convertAndSend("/topic/reply" + session2, messageSession2);
    }

    public void verifyMove(String session, String gameId, MoveMessage move){
        ChessGame game = userGameMap.get(session);
        Gson gson = new Gson();

        if(game.isCurrentPlayer(session)){
            boolean isValidMove = game.makeMove(game.getPlayerColor(session), move.getFromRow(), move.getFromCol(), move.getToRow(), move.getToCol());
            if(isValidMove){
                game.switchTurns();
            }
            else{
                move = new MoveMessage(-1, -1, -1, -1);
            }

            String messageSession1 = gson.toJson(new GameStateMessage(gameId, game.getStringBoard(), false, !isValidMove, move));
            String messageSession2 = gson.toJson(new GameStateMessage(gameId, game.getStringBoard(), false, isValidMove, move));
            messagingTemplate.convertAndSend("/topic/state" + gameId + session, messageSession1);
            messagingTemplate.convertAndSend("/topic/state" + gameId + game.getOpponentName(session), messageSession2);

            if(isValidMove){
                String opponentColor = game.getOpponentColor(session);

                if (game.isCheckMated(opponentColor)) {
                    String terminalMessage = String.format("{\"result\" : \"%s Wins!\",\"condition\" : \"Checkmate\" }", game.getPlayerColor(session));
                    messagingTemplate.convertAndSend("/topic/state" + gameId, terminalMessage);
                }

                if(game.isStaleMate(opponentColor)){
                    String terminalMessage = String.format("{\"result\" : \"Stalemate\",\"condition\" : \"%s\" }", game.getPlayerColor(session));
                    messagingTemplate.convertAndSend("/topic/state" + gameId, terminalMessage);
                }
            }
        }
        else{
            String messageSession1 = gson.toJson(new GameStateMessage(gameId, game.getStringBoard(), false, false, new MoveMessage(-1,-1,-1,-1)));
            messagingTemplate.convertAndSend("/topic/state" + gameId + session, messageSession1);
        }
    }

    public ChessGame getGameBySession(String sessionId) {
        return userGameMap.get(sessionId);
    }

    public void handleDisconnect(String session, String reason){
        ChessGame game = userGameMap.get(session);
        if(game != null){
            if(rematchMap.containsKey(game.getGameId())){
                rematchMap.remove(game.getGameId());
            }
            String opponent = game.getOpponentName(session);
            String terminalMessage = String.format("{\"result\" : \"%s Wins!\",\"condition\" : \"%s\" }", game.getOpponentColor(session), reason);
            messagingTemplate.convertAndSend("/topic/state" + game.getGameId(), terminalMessage);
            if(!reason.equals("Resignation")){
                removeGame(session);
            }
        }
    }

    public void handleRematch(String gameId, String session, String mode){
        if(userGameMap.containsKey(session)){
            if(rematchMap.containsKey(gameId)){
                startRematch(gameId, session, mode);
            }
            else{
                rematchMap.put(gameId, session);
            }
        }
    }

    public void removeGame(String session){
        ChessGame game = userGameMap.get(session);
        String opponent = game.getOpponentName(session);
        userGameMap.remove(session);
        userGameMap.remove(opponent);
    }

}