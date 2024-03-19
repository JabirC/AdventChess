package com.example.adventchess.model;

public class GameStateMessage {
    private String gameId;
    private String[][] gameState;
    private boolean isWhite;
    private boolean turn;

    public GameStateMessage(String gameId, String[][] gameState, boolean isWhite, boolean turn) {
        this.gameId = gameId;
        this.gameState = gameState;
        this.isWhite = isWhite;
        this.turn = turn;
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

    public boolean getIsWhite(){
        return isWhite;
    }

    public void setIsWhite(boolean isWhite){
        this.isWhite = isWhite;
    }

    public boolean getTurn(){
        return turn;
    }

    public void setTurn(boolean turn){
        this.turn = turn;
    }
}