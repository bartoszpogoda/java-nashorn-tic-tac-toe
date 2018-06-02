package game;


import game.drawcondition.DrawConditionChecker;
import game.exception.GameException;
import game.model.Board;
import game.model.BoardEntity;
import game.model.BoardPosition;
import game.move.MoveStrategy;
import game.wincondition.WinConditionChecker;

import java.util.Optional;

public class GameManager {

    private MoveStrategy enemyMoveStrategy;

    private Board gameBoard;

    private int boardSize = 5;

    private BoardEntity playerEntity = BoardEntity.O;

    private BoardEntity aiEntity = BoardEntity.X;

    private final WinConditionChecker winConditionChecker;
    private final DrawConditionChecker drawConditionChecker;

    public GameManager(WinConditionChecker winConditionChecker, DrawConditionChecker drawConditionChecker) {
        this.winConditionChecker = winConditionChecker;
        this.drawConditionChecker = drawConditionChecker;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void startNewGame() throws GameException {

        if (boardSize < 5) {
            throw new GameException("5x5 is minimal size of the board");
        }

        if (enemyMoveStrategy == null) {
            throw new GameException("no strategy was set");
        }

        this.gameBoard = new Board(boardSize);
    }

    public void executePlayerMove(BoardPosition boardPosition) throws GameException {

        if (gameBoard.getEntityAt(boardPosition) != BoardEntity.EMPTY) {
            throw new GameException("board field is not empty");
        }

        gameBoard.setEntityAt(boardPosition, playerEntity);

    }

    public void executeAiMove() {
        BoardPosition boardPosition = enemyMoveStrategy.nextMove(aiEntity, gameBoard);

        gameBoard.setEntityAt(boardPosition, aiEntity);
    }

    public GameState getGameState() {

        Optional<BoardEntity> winner = winConditionChecker.getWinner(gameBoard);

        if (winner.isPresent()) {
            if (winner.get() == playerEntity) {
                return GameState.PLAYER_WIN;
            } else {
                return GameState.AI_WIN;
            }
        }

        if (drawConditionChecker.isDraw(gameBoard)) {
            return GameState.DRAW;
        }

        return GameState.IN_PROGRESS;
    }

    public void setEnemyMoveStrategy(MoveStrategy enemyMoveStrategy) {
        this.enemyMoveStrategy = enemyMoveStrategy;
    }


}


