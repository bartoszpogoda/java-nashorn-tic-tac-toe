var MoveStrategy = Java.type("game.move.MoveStrategy");

var BoardPosition = Java.type("game.model.BoardPosition");
var BoardEntity = Java.type("game.model.BoardEntity");
var Board = Java.type("game.model.Board");

var RandomStrategy = Java.extend(MoveStrategy, {
    nextMove: function(boardEntity, boardSituation) {

        var randomX, randomY;

        do {
            randomX = Math.floor(Math.random() * boardSituation.getSize());
            randomY = Math.floor(Math.random() * boardSituation.getSize());
        } while(boardSituation.getEntityAt(new BoardPosition(randomX, randomY)) !== BoardEntity.EMPTY);

        return new BoardPosition(randomX, randomY);
    }
});

var strategy = new RandomStrategy();

