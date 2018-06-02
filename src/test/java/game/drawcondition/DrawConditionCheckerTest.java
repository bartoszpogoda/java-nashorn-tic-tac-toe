package game.drawcondition;

import game.model.Board;
import game.wincondition.mock.BoardMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DrawConditionCheckerTest {

    private DrawConditionChecker drawConditionChecker;

    @BeforeEach
    void setUp() {
        drawConditionChecker = new DrawConditionChecker();
    }

    @Test
    void shouldGiveCorrectAnswerWhenNotDraw() {
        // given
        Board boardMock = BoardMocks.getFilledWithOExceptFor1on1FieldEmpty();

        // when
        boolean draw = drawConditionChecker.isDraw(boardMock);

        // then
        assertFalse(draw);
    }

    @Test
    void shouldGiveCorrectAnswerWhenDraw() {
        // given
        Board boardMock = BoardMocks.getDrawBoard();

        // when
        boolean draw = drawConditionChecker.isDraw(boardMock);

        // then
        assertTrue(draw);
    }
}