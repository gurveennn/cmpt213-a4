package ca.cmpt213.asn4.memorygame.game;

/**
 * This class checks whether the first and second cards selected are matching, if they are then the two cards stay
 * revealed, otherwise they are hidden again and the move is over
 */

public class GameLogic {
    private final GameBoard gameBoard;
    private Card firstCard;
    private Card secondCard;
    private boolean match;

    public GameLogic(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    //check if you can reveal this card
    public boolean canRevealCard(int row, int col) {
        Card card = gameBoard.getCard(row, col);
        return !card.isRevealed();
    }

    // reveal this card
    public String revealCard(int row, int col) {
        Card card = gameBoard.getCard(row, col);
        card.reveal();
        if(firstCard==null) {
            firstCard = card;
        } else {
            secondCard = card;
            match = firstCard.getImage().equals(secondCard.getImage());
        }
        return card.getImage();
    }

    public boolean isMatch() {
        return match;
    }

    // if you can hide the cards
    public boolean canHideCards() {
        return firstCard!=null && secondCard!=null && !match;
    }

    public void hideCards() {
        if (firstCard != null && secondCard != null) {
            firstCard.unreveal();
            secondCard.unreveal();
            resetCards();
        }
    }

    public void resetCards(){
        firstCard = null;
        secondCard = null;
        match = false;
    }


}
