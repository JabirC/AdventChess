package com.example.adventchess.model;

public class GameStateMessage {
    private String gameId;
    private String[][] gameState;

    public GameStateMessage(String gameId, String[][] gameState) {
        this.gameId = gameId;
        this.gameState = gameState;
    }

    // Getters and setters (not necessary for Gson serialization)
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String[][] getGameState() {
        return gameState;
    }

    public void setGameState(String[][] gameState) {
        this.gameState = gameState;
    }
}