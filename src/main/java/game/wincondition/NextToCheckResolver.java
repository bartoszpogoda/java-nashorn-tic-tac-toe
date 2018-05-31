package game.wincondition;

import com.sun.istack.internal.NotNull;
import game.model.Board;
import game.model.BoardPosition;

import java.util.Optional;

public class NextToCheckResolver {

    /**
     * Strategy for traversing map when checking win condition.
     */
    interface NextStrategy {
        BoardPosition next(BoardPosition current);
    }

    private final NextStrategy nextStrategy;

    public NextToCheckResolver(final NextStrategy nextStrategy) {
        this.nextStrategy = nextStrategy;
    }

    private Optional<BoardPosition> checkInBounds(final Board board, @NotNull final BoardPosition position) {
        if (position.getX() < 0 || position.getX() >= board.getSize() || position.getY() < 0
                || position.getY() >= board.getSize()) {
            return Optional.empty();
        } else {
            return Optional.of(position);
        }
    }

    public Optional<BoardPosition> resolve(final Board board, final BoardPosition current) {
        BoardPosition newPosition = nextStrategy.next(current);

        return checkInBounds(board, newPosition);
    }
}



