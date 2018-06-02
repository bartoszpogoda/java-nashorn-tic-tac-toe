package gui.event;

import game.model.BoardPosition;

import java.util.EventObject;

public class BoardFieldClicked extends EventObject {

    private final BoardPosition boardPosition;

    public BoardFieldClicked(Object source, BoardPosition boardPosition) {
        super(source);
        this.boardPosition = boardPosition;
    }

    public BoardPosition getBoardPosition() {
        return boardPosition;
    }

    @Override
    public String toString() {
        return "BoardFieldClicked{" +
                "boardPosition=" + boardPosition +
                '}';
    }
}
