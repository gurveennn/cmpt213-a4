package ca.cmpt213.asn4.memorygame.ui;

import ca.cmpt213.asn4.memorygame.game.Card;
import ca.cmpt213.asn4.memorygame.game.GameBoard;
import ca.cmpt213.asn4.memorygame.game.GameLogic;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * in this class it creates a window for a new game and checks if the cards selected are the same
 * it also keeps tracks of the number of moves and refreshes the game board when the player clicks on new game
 */

public class MemoryGame extends Application {

    private final int GRID_SIZE = 4;
    private final ImageView[][] imageViews = new ImageView[GRID_SIZE][GRID_SIZE];
    private final GameBoard gameBoard = new GameBoard(GRID_SIZE);
    private final GameLogic gameLogic = new GameLogic(gameBoard);
    private int moves = 0;
    private Label movesLabel;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Memory Game :)");
        VBox vbox1 = new VBox();
        GridPane grid = new GridPane();

        initializeGrid(grid);
        Button newGameButton = new Button("New Game");
        movesLabel = new Label("Moves: " + moves);
        movesLabel.setFont(new Font(30.0));
        movesLabel.setPadding(new Insets(0,0,15,0));

        newGameButton.setOnAction(event -> resetGame(grid));
        newGameButton.setFont(new Font(20.0));

        vbox1.getChildren().addAll(grid, movesLabel, newGameButton);
        vbox1.setAlignment(Pos.CENTER);
        newGameButton.setPadding(new Insets(10,10,10,10));

        Scene scene = new Scene(vbox1, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void initializeGrid(GridPane grid) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                ImageView imageView = new ImageView();
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                imageView.setImage(new Image("file:images/hidden.png"));
                int r = row;
                int c = col;
                imageView.setOnMouseClicked(e -> handleCardClick(r, c, imageView));
                grid.add(imageView, col, row);
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setAlignment(Pos.CENTER);
                grid.setPadding(new Insets(5,0,20,0));
                imageViews[r][c] = imageView;


            }
        }
    }

    private void handleCardClick(int row, int col, ImageView imageView) {
        // already revealed
        if (!gameLogic.canRevealCard(row,col)) {
            return;
        }

        String imagePath = gameLogic.revealCard(row,col);
        imageView.setImage(new Image(imagePath));

        if(gameLogic.isMatch()) {
            // keep both cards revealed
            gameLogic.resetCards();
            updateMoves();


        } else if( gameLogic.canHideCards()) {
            //hide the cards
            hideCards();
            updateMoves();

        }
    }

    private void hideCards() {
        PauseTransition pause = new PauseTransition(Duration.seconds(0.3));
        pause.setOnFinished(event -> {
            gameLogic.hideCards();
            updateGrid();

        });
        pause.play();
    }

    private void updateGrid() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Card card = gameBoard.getCard(row, col);
                if(card.isRevealed()){
                    imageViews[row][col].setImage(new Image(card.getImage()));
                } else {
                    imageViews[row][col].setImage(new Image("file:images/hidden.png"));
                }
            }
        }
    }

    //restart game
    private void resetGame(GridPane grid) {
        gameBoard.resetBoard();
        gameLogic.resetCards();
        moves = 0;
        movesLabel.setText("Moves: " + moves);
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                imageViews[row][col].setImage(new Image("file:images/hidden.png"));
            }
        }
    }

    private void updateMoves() {
        moves++;
        movesLabel.setText("Moves: " + moves);
    }
}
