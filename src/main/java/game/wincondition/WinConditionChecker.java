package game.wincondition;

import game.model.Board;
import game.model.BoardEntity;
import game.model.BoardPosition;

import java.util.Optional;

public class WinConditionChecker {

    private final CheckDirection[] DIRECTIONS_TO_CHECK = new CheckDirection[]{
            CheckDirection.DIAGONAL_TO_BOTTOM_RIGHT,
            CheckDirection.DIAGONAL_TO_BOTTOM_LEFT,
            CheckDirection.TOP_TO_BOTTOM,
            CheckDirection.LEFT_TO_RIGHT
    };

    private final int IN_LINE_TO_WIN = 5;

    public Optional<BoardEntity> getWinner(Board board) {

        for (int x = 0; x < board.getSize(); x++) {
            for (int y = 0; y < board.getSize(); y++) {
                BoardPosition boardPosition = new BoardPosition(x, y);
                BoardEntity entity = board.getEntityAt(boardPosition);

                if (entity != BoardEntity.EMPTY && isWinner(board, entity, boardPosition)) {
                    return Optional.of(entity);
                }
            }
        }

        return Optional.empty();
    }

    private boolean isWinner(Board board, BoardEntity entity, BoardPosition boardPosition) {
        NextToCheckResolverFactory nextToCheckResolverFactory = NextToCheckResolverFactory.getInstance();

        for (CheckDirection direction : DIRECTIONS_TO_CHECK) {

            NextToCheckResolver nextToCheckResolver = nextToCheckResolverFactory.forDirection(direction);

            int linedCount = 1;

            Optional<BoardPosition> nextPositionOpt = nextToCheckResolver.resolve(board, boardPosition);

            while (nextPositionOpt.isPresent()) {
                BoardPosition nextPosition = nextPositionOpt.get();
                BoardEntity entityAt = board.getEntityAt(nextPosition);

                if (entityAt.equals(entity)) {
                    linedCount++;

                    if (linedCount >= IN_LINE_TO_WIN) {
                        return true;
                    }
                } else {
                    break;
                }

                nextPositionOpt = nextToCheckResolver.resolve(board, nextPosition);
            }
        }
        return false;
    }

}


