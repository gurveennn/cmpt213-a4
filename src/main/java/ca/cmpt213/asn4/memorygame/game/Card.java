package ca.cmpt213.asn4.memorygame.game;

/**
 * This class creates a card object to be used in the game grid,
 * it also keeps track of which card is revealed which is not
 */

public class Card {
    private final String image;
    private boolean revealed;

    public Card(String igmage){
        this.image = igmage;
        this.revealed = false;
    }

    public String getImage() {
        return image;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void reveal() {
        revealed = true;
    }

    public void unreveal() {
        revealed = false;
    }
}
