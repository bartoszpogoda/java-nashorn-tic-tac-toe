package gui.board;

import game.model.Board;
import game.model.BoardEntity;
import game.model.BoardPosition;
import gui.event.BoardFieldClicked;
import gui.event.BoardFieldClickedListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class BoardCanvas extends Canvas {

    private double fieldWidth = 0.0;
    private double fieldHeight = 0.0;

    private double symbolWidth = 0.0;
    private double symbolHeight = 0.0;

    private BoardFieldClickedListener boardFieldClickedListener;

    public BoardCanvas() {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(boardFieldClickedListener != null) {
                boardFieldClickedListener.boardFieldClicked(fromMouseEvent(event));
            }
        });
    }

    public void drawBoard(Board gameBoard) {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.clearRect(0,0,getWidth(),getHeight());

        fieldWidth = this.getWidth() / gameBoard.getSize();
        fieldHeight = this.getHeight() / gameBoard.getSize();

        symbolWidth = fieldWidth * 0.6;
        symbolHeight = fieldHeight * 0.6;

        drawLines(gc, gameBoard.getSize());
        drawBoard(gc, gameBoard);
    }

    private void drawLines(GraphicsContext gc, int boardSize) {
        for (int i = 1; i < boardSize; i++) {
            gc.setFill(Color.GREEN);
            gc.setStroke(Color.valueOf("#504D4B"));
            gc.setLineWidth(4);
            gc.strokeLine(fieldWidth * i, 0, fieldWidth * i, this.getHeight());
            gc.strokeLine(0, fieldHeight * i, this.getWidth(), fieldHeight * i);
        }
    }

    private void drawBoard(GraphicsContext gc, Board gameBoard) {
        for (int x = 0; x < gameBoard.getSize(); x++) {
            for(int y = 0 ; y < gameBoard.getSize() ; y++) {
                drawSymbol(gc, fieldWidth/2 + fieldWidth*x, fieldHeight/2 + fieldHeight*y, gameBoard.getEntityAt(new BoardPosition(x, y)));
            }
        }
    }

    private void drawSymbol(GraphicsContext gc, double x, double y, BoardEntity entityAt) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.valueOf("#504D4B"));
        gc.setLineWidth(4);

        if(entityAt == BoardEntity.O) {
            gc.strokeOval(x - symbolWidth / 2, y - symbolHeight/2 , symbolWidth, symbolHeight);
        } else if (entityAt == BoardEntity.X) {
            gc.strokeLine(x - symbolWidth / 2, y - symbolHeight/2, x + symbolWidth / 2, y + symbolHeight/2);
            gc.strokeLine(x + symbolWidth / 2, y - symbolHeight/2, x - symbolWidth / 2, y + symbolHeight/2);
        }
    }

    private BoardFieldClicked fromMouseEvent(MouseEvent mouseEvent) {
        int x = (int)(mouseEvent.getX() / fieldWidth);
        int y = (int)(mouseEvent.getY() / fieldHeight);

        return new BoardFieldClicked(this, new BoardPosition(x, y));
    }

    public void setBoardFieldClickedListener(BoardFieldClickedListener listener) {
        boardFieldClickedListener = listener;
    }

}
