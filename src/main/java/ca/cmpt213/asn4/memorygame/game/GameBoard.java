package ca.cmpt213.asn4.memorygame.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class creates a grid of Cards and sets the image for the cards to be used in the UI
 */

public class GameBoard {
    private final int gridSize;
    private Card[][] board;

    public GameBoard(int gridSize) {
        this.gridSize = gridSize;
        createBoard();
    }

    private void createBoard() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < (gridSize * gridSize) / 2; i++) {
            cards.add(new Card("file:images/image"+ (i+1) +".png"));
            cards.add(new Card("file:images/image" + (i+1) +".png"));
        }
        //randomize the list
        Collections.shuffle(cards);

        board = new Card[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                board[i][j] = cards.removeFirst();
            }
        }
    }

    public Card getCard(int row, int col) {
        return board[row][col];
    }

    public void resetBoard() {
        createBoard();
    }


}
