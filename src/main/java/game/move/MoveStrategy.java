package game.move;

import game.model.Board;
import game.model.BoardEntity;
import game.model.BoardPosition;

public interface MoveStrategy {
    BoardPosition nextMove(BoardEntity boardEntity, Board boardSituation);

    default String getIdentifier() { return "Strategy"; }
}


