package game.model;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Board {

    private final int size;

    private final BoardEntity[][] boardState;

    public Board(int size) {
        this.size = size;

        boardState = new BoardEntity[size][size];
        clearBoard();
    }

    private void clearBoard() {
        IntStream.range(0, boardState.length).forEach(x -> Arrays.fill(boardState[x], BoardEntity.EMPTY));
    }

    public int getSize() {
        return size;
    }

    public BoardEntity getEntityAt(BoardPosition boardPosition) {
        return boardState[boardPosition.getX()][boardPosition.getY()];
    }
}
