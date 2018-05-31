package game.wincondition;

import game.model.BoardPosition;

public class NextToCheckResolverFactory {

    private static NextToCheckResolverFactory instance;

    public static NextToCheckResolverFactory getInstance() {
        return instance != null ? instance : (instance = new NextToCheckResolverFactory());
    }

    public NextToCheckResolver forDirection(CheckDirection direction) {

        NextToCheckResolver produced = null;

        switch (direction) {
            case LEFT_TO_RIGHT:
                produced = new NextToCheckResolver(current -> new BoardPosition(current.getX() + 1, current.getY()));
                break;
            case TOP_TO_BOTTOM:
                produced = new NextToCheckResolver(current -> new BoardPosition(current.getX(), current.getY() + 1));
                break;
            case DIAGONAL_TO_BOTTOM_LEFT:
                produced = new NextToCheckResolver(current -> new BoardPosition(current.getX() - 1, current.getY() + 1));
                break;
            case DIAGONAL_TO_BOTTOM_RIGHT:
                produced = new NextToCheckResolver(current -> new BoardPosition(current.getX() + 1, current.getY() + 1));
                break;
        }

        return produced;

    }

}
