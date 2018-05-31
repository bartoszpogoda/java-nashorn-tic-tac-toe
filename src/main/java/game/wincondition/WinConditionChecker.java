package game.wincondition;

import game.model.Board;
import game.model.BoardEntity;
import game.model.BoardPosition;

import java.util.Optional;
import java.util.stream.Stream;

public class WinConditionChecker {

    private final CheckDirection[] DIRECTIONS_TO_CHECK = new CheckDirection[]{
            CheckDirection.DIAGONAL_TO_BOTTOM_RIGHT,
            CheckDirection.DIAGONAL_TO_BOTTOM_LEFT,
            CheckDirection.TOP_TO_BOTTOM,
            CheckDirection.LEFT_TO_RIGHT
    };

    private final int IN_LINE_TO_WIN = 5;

    private NextToCheckResolverFactory nextToCheckResolverFactory = NextToCheckResolverFactory.getInstance();

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
        return Stream.of(DIRECTIONS_TO_CHECK).map(direction -> countInLine(direction, board, boardPosition, entity)).anyMatch(count -> count >= IN_LINE_TO_WIN);
    }

    private int countInLine(CheckDirection direction, Board board, BoardPosition startPosition, BoardEntity entity) {

        NextToCheckResolver nextToCheckResolver = nextToCheckResolverFactory.forDirection(direction);

        int countInLine = 0;
        Optional<BoardPosition> currentPositionOpt = Optional.of(startPosition);

        while (currentPositionOpt.isPresent() && board.getEntityAt(currentPositionOpt.get()).equals(entity)) {
            countInLine++;

            currentPositionOpt = nextToCheckResolver.resolve(board, currentPositionOpt.get());
        }

        return countInLine;
    }

}


