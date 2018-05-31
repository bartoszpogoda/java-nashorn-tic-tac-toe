import game.model.Board;
import game.wincondition.WinConditionChecker;

public class App {

    public static void main(String[] args) {
        Board board = new Board(5);

        WinConditionChecker winConditionChecker = new WinConditionChecker();
        winConditionChecker.getWinner(board);

    }

}
