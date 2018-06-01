package game.move;

import game.model.Board;
import game.model.BoardEntity;
import game.model.BoardPosition;
import game.wincondition.WinConditionChecker;
import game.wincondition.mock.BoardMocks;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RandomMoveStrategyTest {

    private static final String JS_FILENAME = "src/main/resources/move_strategies/randomStrategy.js";

    private static MoveStrategy strategy;

    @BeforeAll
    static void setUp() throws FileNotFoundException, ScriptException {

        strategy = new MoveStrategyFromJSFile().load(new File(JS_FILENAME));
    }

    @Test
    void shouldGenerateProperMove() throws FileNotFoundException, ScriptException {
        // given
        Board board = new Board(5);
        BoardEntity boardEntity = BoardEntity.X;

        // when
        BoardPosition boardPosition = strategy.nextMove(boardEntity, board);

        System.out.println(String.format("Random position was: [%d,%d]", boardPosition.getX(), boardPosition.getY()));

        // then
        assertTrue(boardPosition.getX() >= 0 && boardPosition.getX() < board.getSize());
        assertTrue(boardPosition.getY() >= 0 && boardPosition.getY() < board.getSize());
    }

    @Test
    void shouldGenerateProperMoveWhenOnlyOneMoveIsValid() throws FileNotFoundException, ScriptException {
        // given
        Board boardMock = BoardMocks.getFilledWithOExceptFor1on1FieldEmpty();
        BoardEntity boardEntity = BoardEntity.X;

        // when
        BoardPosition boardPosition = strategy.nextMove(boardEntity, boardMock);

        // then
        assertEquals(1, boardPosition.getX());
        assertEquals(1, boardPosition.getY());
    }

}
