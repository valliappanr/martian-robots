package uk.co.meenasoft.martian.model.layout;

public class EastOrientation implements Orientation {

    private static final EastOrientation INSTANCE = new EastOrientation();

    public static final EastOrientation getInstance() {
        return INSTANCE;
    }

    private EastOrientation() {

    }

    @Override
    public Direction turnLeft() {
        return Direction.NORTH;
    }

    @Override
    public Direction turnRight() {
        return Direction.SOUTH;
    }
}
