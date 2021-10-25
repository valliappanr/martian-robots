package uk.co.meenasoft.martian.model.layout;

public class SouthOrientation implements Orientation {
    private static final SouthOrientation INSTANCE = new SouthOrientation();
    public static final SouthOrientation getInstance() {
        return INSTANCE;
    }

    private SouthOrientation() {

    }

    @Override
    public Direction turnLeft() {
        return Direction.EAST;
    }

    @Override
    public Direction turnRight() {
        return Direction.WEST;
    }
}
