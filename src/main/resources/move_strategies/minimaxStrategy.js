var MoveStrategy = Java.type("game.move.MoveStrategy");

var BoardPosition = Java.type("game.model.BoardPosition");
var BoardEntity = Java.type("game.model.BoardEntity");
var Board = Java.type("game.model.Board");


var DrawConditionChecker = Java.type("game.drawcondition.DrawConditionChecker");
var WinConditionChecker = Java.type("game.wincondition.WinConditionChecker");

var winConditionChecker = new WinConditionChecker();
var drawConditionChecker = new DrawConditionChecker();

var AI_ENTITY = BoardEntity.X;
var HUMAN_ENTITY = BoardEntity.O;

var MAX_DEPTH = 3;

var MinimaxStrategy = Java.extend(MoveStrategy, {
    nextMove: function (boardEntity, boardSituation) {
        var boardCopy = this.copyBoard(boardSituation);

        var result = this.minimax(0, true, boardCopy);

        return result.move;
    },

    getIdentifier: function () {
        return "Minimax move";
    },

    minimax: function (currentDepth, isAiMove, board) {
        if (currentDepth >= MAX_DEPTH) {
            return {score: 0};
        }

        var gameState = this.getGameState(board);

        if (gameState !== 0) {
            return {score: this.score(board, gameState, currentDepth)};
        }

        var entity = isAiMove ? AI_ENTITY : HUMAN_ENTITY;
        var possibleMoves = this.getPossibleMoves(board);
        var scores = new Array();

        var self = this;
        possibleMoves.forEach(function(move) {
            self.executeMove(board, move, entity);
            scores.push(self.minimax(currentDepth + 1, !isAiMove, board).score);
            self.undoMove(board, move);
        });

        var index;
        if (isAiMove) {
            var maxScore = Math.max.apply(Math, scores);
            index = scores.indexOf(maxScore);
            return {score: scores[index], move: possibleMoves[index]};
        } else {
            var minScore = Math.min.apply(Math, scores);
            index = scores.indexOf(minScore);
            return {score: scores[index], move: possibleMoves[index]};
        }

    },

    getPossibleMoves: function (board) {
        var possibleMoves = new Array();

        for (var x = 0; x < board.getSize(); x++) {
            for (var y = 0; y < board.getSize(); y++) {
                if(board.getEntityAt(new BoardPosition(x, y)) === BoardEntity.EMPTY) {
                    possibleMoves.push(new BoardPosition(x, y));
                }
            }
        }

        return possibleMoves;
    },

    executeMove: function (board, position, entity) {
        board.setEntityAt(position, entity);
    },

    undoMove: function (board, position) {
        board.setEntityAt(position, BoardEntity.EMPTY);
    },

    copyBoard: function (board) {
        var newBoard = new Board(board.getSize());

        for (var x = 0; x < board.getSize(); x++) {
            for (var y = 0; y < board.getSize(); y++) {
                var boardPosition = new BoardPosition(x, y);

                newBoard.setEntityAt(boardPosition, board.getEntityAt(boardPosition));
            }
        }

        return newBoard;
    },

    /**
     * Returns:
     *  0 when in progress
     *  1 when draw
     *  2 when human won
     *  3 when ai won
     */
    getGameState: function (boardSituation) {
        var winner = winConditionChecker.getWinner(boardSituation);

        if (winner.isPresent()) {
            if (winner.get() === AI_ENTITY) {
                return 3;
            } else {
                return 2;
            }
        }

        if (drawConditionChecker.isDraw(boardSituation)) {
            return 1;
        }

        return 0;
    },

    /**
     * Scores the final situation.
     */
    score: function (board, gameState, depth) {
        var maxScore = board.getSize() * board.getSize() + 1;

        if (gameState === 1)
            return 0;
        else if (gameState === 2)
            return depth - maxScore;
        else if (gameState === 3)
            return maxScore - depth;
    }

});

var strategy = new MinimaxStrategy();

