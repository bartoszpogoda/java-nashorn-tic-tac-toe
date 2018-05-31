package game.wincondition.mock;

import game.model.Board;
import game.model.BoardEntity;
import game.model.BoardPosition;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class BoardMocks {

    public static Board getWinningOToRightDiagonal() {
        Board boardMock = Mockito.mock(Board.class);

        when(boardMock.getSize()).thenReturn(10);

        when(boardMock.getEntityAt(any())).thenReturn(BoardEntity.EMPTY);
        when(boardMock.getEntityAt(new BoardPosition(1, 1))).thenReturn(BoardEntity.O);
        when(boardMock.getEntityAt(new BoardPosition(2, 2))).thenReturn(BoardEntity.O);
        when(boardMock.getEntityAt(new BoardPosition(3, 3))).thenReturn(BoardEntity.O);
        when(boardMock.getEntityAt(new BoardPosition(4, 4))).thenReturn(BoardEntity.O);
        when(boardMock.getEntityAt(new BoardPosition(5, 5))).thenReturn(BoardEntity.O);

        return boardMock;
    }

    public static Board getWinningXHorizontal() {
        Board boardMock = Mockito.mock(Board.class);

        when(boardMock.getSize()).thenReturn(5);

        when(boardMock.getEntityAt(any())).thenReturn(BoardEntity.EMPTY);
        when(boardMock.getEntityAt(new BoardPosition(0, 2))).thenReturn(BoardEntity.X);
        when(boardMock.getEntityAt(new BoardPosition(1, 2))).thenReturn(BoardEntity.X);
        when(boardMock.getEntityAt(new BoardPosition(2, 2))).thenReturn(BoardEntity.X);
        when(boardMock.getEntityAt(new BoardPosition(3, 2))).thenReturn(BoardEntity.X);
        when(boardMock.getEntityAt(new BoardPosition(4, 2))).thenReturn(BoardEntity.X);

        return boardMock;
    }

    public static Board getNoWinner() {
        Board boardMock = Mockito.mock(Board.class);

        when(boardMock.getSize()).thenReturn(5);

        when(boardMock.getEntityAt(any())).thenReturn(BoardEntity.EMPTY);
        when(boardMock.getEntityAt(new BoardPosition(0, 2))).thenReturn(BoardEntity.X);
        when(boardMock.getEntityAt(new BoardPosition(1, 2))).thenReturn(BoardEntity.X);
        when(boardMock.getEntityAt(new BoardPosition(2, 2))).thenReturn(BoardEntity.X);
        when(boardMock.getEntityAt(new BoardPosition(3, 2))).thenReturn(BoardEntity.X);
        when(boardMock.getEntityAt(new BoardPosition(4, 2))).thenReturn(BoardEntity.O);
        when(boardMock.getEntityAt(new BoardPosition(4, 0))).thenReturn(BoardEntity.O);
        when(boardMock.getEntityAt(new BoardPosition(4, 1))).thenReturn(BoardEntity.O);
        when(boardMock.getEntityAt(new BoardPosition(4, 3))).thenReturn(BoardEntity.O);

        return boardMock;
    }
}
