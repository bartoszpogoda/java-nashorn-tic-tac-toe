package game.wincondition;

import game.model.Board;
import game.model.BoardEntity;
import game.wincondition.mock.BoardMocks;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WinConditionCheckerTest {

    private static WinConditionChecker winConditionChecker;

    @BeforeAll
    static void setUp() {
        winConditionChecker = new WinConditionChecker();
    }

    @Test
    void getWinnerODiagonal() {
        // given
        Board boardMock = BoardMocks.getWinningOToRightDiagonal();

        // when
        Optional<BoardEntity> winner = winConditionChecker.getWinner(boardMock);

        // then
        assertTrue(winner.isPresent());
        assertEquals(BoardEntity.O, winner.get());
    }

    @Test
    void getWinnerXHorizontal() {
        // given
        Board boardMock = BoardMocks.getWinningXHorizontal();

        // when
        Optional<BoardEntity> winner = winConditionChecker.getWinner(boardMock);

        // then
        assertTrue(winner.isPresent());
        assertEquals(BoardEntity.X, winner.get());
    }

    @Test
    void getWinnerWhenNoWinner() {
        // given
        Board boardMock = BoardMocks.getNoWinner();

        // when
        Optional<BoardEntity> winner = winConditionChecker.getWinner(boardMock);

        // then
        assertFalse(winner.isPresent());
    }
}