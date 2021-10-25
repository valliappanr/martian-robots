package uk.co.meenasoft.martian.model.layout;

public class WestOrientation implements Orientation {

    private static final WestOrientation INSTANCE = new WestOrientation();

    public static final WestOrientation getInstance() {
        return INSTANCE;
    }

    private WestOrientation() {

    }

    @Override
    public Direction turnLeft() {
        return Direction.SOUTH;
    }

    @Override
    public Direction turnRight() {
        return Direction.NORTH;
    }
}
