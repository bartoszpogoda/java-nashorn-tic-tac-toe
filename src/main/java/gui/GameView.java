package gui;

import game.GameManager;
import game.GameState;
import game.StrategyManager;
import game.exception.FieldOccupiedException;
import game.exception.GameException;
import game.exception.StrategyNotSetException;
import game.move.MoveStrategy;
import gui.board.BoardCanvas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameView implements Initializable {


    private Stage primaryStage;

    private GameManager gameManager;

    private StrategyManager strategyManager;

    private GameState gameState = GameState.IN_PROGRESS;

    @FXML
    private BoardCanvas boardCanvas;

    @FXML
    private Button btnLoadStrategies;

    @FXML
    private Button btnRestartGame;

    @FXML
    private ComboBox<MoveStrategy> cbStrategies;

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

        btnLoadStrategies.setOnMouseClicked(mouseEvent -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();

            Optional<File> selectedDirectory = Optional.ofNullable(directoryChooser.showDialog(primaryStage));

            if (selectedDirectory.isPresent()) {
                strategyManager.loadFromDirectory(selectedDirectory.get());
                List<MoveStrategy> loadedStrategies = strategyManager.getLoadedStrategies();

                cbStrategies.getItems().clear();
                cbStrategies.getItems().addAll(loadedStrategies);
            }
        });

        boardCanvas.setBoardFieldClickedListener(boardFieldClicked -> {
            try {
                if (gameManager.getGameState() == GameState.IN_PROGRESS) {
                    gameManager.executePlayerMove(boardFieldClicked.getBoardPosition());
                    boardCanvas.drawBoard(gameManager.getGameBoard());

                    checkAndHandleGameState();
                }

                if (gameManager.getGameState() == GameState.IN_PROGRESS) {
                    gameManager.executeAiMove();
                    boardCanvas.drawBoard(gameManager.getGameBoard());

                    checkAndHandleGameState();
                }

            } catch (FieldOccupiedException e) {
                System.out.println("Field is not empty. Cannot execute the move.");
            } catch (StrategyNotSetException e) {
                showMessage("First select some strategy for your opponent.");
            }
        });

        cbStrategies.setCellFactory(listView -> new ListCell<MoveStrategy>() {
            @Override
            protected void updateItem(MoveStrategy strategy, boolean empty) {
                super.updateItem(strategy, empty);
                if (strategy == null || empty) {
                    setGraphic(null);
                } else {
                    setText(strategy.getIdentifier());
                }
            }

        });

        cbStrategies.setConverter(new StringConverter<MoveStrategy>() {
            @Override
            public String toString(MoveStrategy strategy) {
                return strategy == null ? null : strategy.getIdentifier();
            }

            @Override
            public MoveStrategy fromString(String string) {
                return null;
            }
        });

        cbStrategies.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != oldValue) {
                gameManager.setEnemyMoveStrategy(newValue);
            }
        });
    }

    private void checkAndHandleGameState() {
        GameState gameState = gameManager.getGameState();

        if (gameState == GameState.PLAYER_WIN) {
            showMessage("Congratulations! You won!");
        } else if (gameState == GameState.AI_WIN) {
            showMessage("You lost!");
        } else if (gameState == GameState.DRAW) {
            showMessage("Draw!");
        }
    }

    public void startNewGame() {
        try {
            gameManager.startNewGame();
            boardCanvas.drawBoard(gameManager.getGameBoard());

        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initStyle(StageStyle.UTILITY);

        alert.showAndWait();
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setStrategyManager(StrategyManager strategyManager) {
        this.strategyManager = strategyManager;
    }
}
