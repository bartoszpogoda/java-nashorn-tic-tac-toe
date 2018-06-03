var MoveStrategy = Java.type("game.move.MoveStrategy");

var BoardPosition = Java.type("game.model.BoardPosition");
var BoardEntity = Java.type("game.model.BoardEntity");
var Board = Java.type("game.model.Board");

var FirstAvailableStrategy = Java.extend(MoveStrategy, {
    nextMove: function(boardEntity, boardSituation) {

        for(var y = 0 ; y < boardSituation.getSize() ; y++) {
            for(var x = 0 ; x < boardSituation.getSize() ; x++) {
                if(boardSituation.getEntityAt(new BoardPosition(x, y)) === BoardEntity.EMPTY) {
                    return new BoardPosition(x, y);
                }
            }
        }

        return null;
    },

    getIdentifier: function() {
        return "First available move";
    }
});

var strategy = new FirstAvailableStrategy();

