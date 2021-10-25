package uk.co.meenasoft.martian.model.layout;

public class NorthOrientation implements Orientation {

    private static final NorthOrientation INSTANCE = new NorthOrientation();
    public static final NorthOrientation getInstance() {
        return INSTANCE;
    }

    private NorthOrientation() {
    }

    @Override
    public Direction turnLeft() {
        return Direction.WEST;
    }

    @Override
    public Direction turnRight() {
        return Direction.EAST;
    }
}
