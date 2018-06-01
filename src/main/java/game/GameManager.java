package game;


import game.exception.GameException;
import game.model.Board;
import game.move.MoveStrategy;

public class GameManager {

    private MoveStrategy enemyMoveStrategy;

    private Board gameBoard;

    private int boardSize = 5;

    public GameManager() {

    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public void startNewGame() {

        if(boardSize < 5) {
            throw new GameException("5x5 is minimal size of the board");
        }

        if(enemyMoveStrategy != null) {
            throw new GameException("no strategy was set");
        }

        this.gameBoard = new Board(boardSize);
    }

    public void setEnemyMoveStrategy(MoveStrategy enemyMoveStrategy) {
        this.enemyMoveStrategy = enemyMoveStrategy;
    }


}


