import game.GameManager;
import game.drawcondition.DrawConditionChecker;
import game.move.MoveStrategy;
import game.move.MoveStrategyFromJSFile;
import game.wincondition.WinConditionChecker;
import gui.GameView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class App extends Application {

    private Stage primaryStage;
    private AnchorPane mainLayout;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, ScriptException {
        // Construct game
        WinConditionChecker winConditionChecker = new WinConditionChecker();
        DrawConditionChecker drawConditionChecker = new DrawConditionChecker();

        GameManager gameManager = new GameManager(winConditionChecker, drawConditionChecker);
        gameManager.setBoardSize(10);

        // temp
        MoveStrategy strategy = new MoveStrategyFromJSFile().load(new File("src/main/resources/move_strategies/randomStrategy.js"));
        gameManager.setEnemyMoveStrategy(strategy);

        // Construct GUI
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Tic-Tac-Toe");

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("gui/GameView.fxml"));
            mainLayout = (AnchorPane) loader.load();

            GameView gameViewController = (GameView) loader.getController();
            gameViewController.setStage(primaryStage);
            gameViewController.setGameManager(gameManager);
            gameViewController.startNewGame();

            Scene scene = new Scene(mainLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
