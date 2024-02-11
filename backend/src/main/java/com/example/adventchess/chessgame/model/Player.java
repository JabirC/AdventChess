package adventchess.chessgame.model;

public class Player {
    private String name;
    private String color;
    private boolean turn;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        if(color.equals("White")){
            this.turn = true;
        }
        else {
            this.turn = false;
        }
    }

    // Getter methods for name and color

    public String getName(){
        return name;
    }

    public String getColor(){
        return color;
    }

    public boolean isTurn(){
        return turn;
    }

    // Other methods or attributes related to the player
}