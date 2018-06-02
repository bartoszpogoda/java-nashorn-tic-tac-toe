package game.drawcondition;

import game.model.Board;
import game.model.BoardEntity;
import game.model.BoardPosition;

import java.util.stream.IntStream;

public class DrawConditionChecker {
    public boolean isDraw(Board board) {

        for (int x = 0; x < board.getSize(); x++) {
            for (int y = 0; y < board.getSize(); y++) {

                if (board.getEntityAt(new BoardPosition(x, y)) == BoardEntity.EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }
}
