package gui;

import game.GameManager;
import game.GameState;
import game.exception.GameException;
import game.model.Board;
import game.model.BoardEntity;
import gui.board.BoardCanvas;
import gui.event.BoardFieldClicked;
import gui.event.BoardFieldClickedListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameView implements Initializable {

    private Stage primaryStage;

    private GameManager gameManager;

    private GameState gameState = GameState.IN_PROGRESS;

    @FXML
    private BoardCanvas boardCanvas;

    @FXML
    private Button btnRestartGame;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnRestartGame.setOnMouseClicked(mouseEvent -> {
            try {
                gameManager.startNewGame();
                boardCanvas.drawBoard(gameManager.getGameBoard());
            } catch (GameException e) {
                e.printStackTrace();
            }
        });

        boardCanvas.setBoardFieldClickedListener(boardFieldClicked -> {
            try {
                gameManager.executePlayerMove(boardFieldClicked.getBoardPosition());
                boardCanvas.drawBoard(gameManager.getGameBoard());

                gameState = gameManager.getGameState();
                System.out.println(gameState);

                gameManager.executeAiMove();
                boardCanvas.drawBoard(gameManager.getGameBoard());

                gameState = gameManager.getGameState();
                System.out.println(gameState);

            } catch (GameException e) {
                e.printStackTrace();
            }
        });
    }

    public void startNewGame() {
        try {
            gameManager.startNewGame();
            boardCanvas.drawBoard(gameManager.getGameBoard());

        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
