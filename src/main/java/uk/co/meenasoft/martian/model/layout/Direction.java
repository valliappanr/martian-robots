package uk.co.meenasoft.martian.model.layout;

import uk.co.meenasoft.martian.model.action.movement.*;

import java.util.Arrays;
import java.util.Optional;

public enum Direction {
    NORTH("N", NorthOrientation.getInstance(), NorthForwardMovement.getInstance()),
    EAST("E", EastOrientation.getInstance(), EastForwardMovement.getInstance()),
    SOUTH("S", SouthOrientation.getInstance(), SouthForwardMovement.getInstance()),
    WEST("W", WestOrientation.getInstance(), WestForwardMovement.getInstance());

    private Orientation orientation;
    private Movement movement;
    private String shortName;

    Direction(String shortName, Orientation orientation, Movement movement) {
        this.orientation = orientation;
        this.movement = movement;
        this.shortName = shortName;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Movement getMovement() {
            return movement;
    }
    public String getShortName() {
        return shortName;
    }
    public static Optional<Direction> getDirection(Orientation orientation) {
        return Arrays.stream(values()).filter(o -> o.getOrientation().equals(orientation)).findFirst();
    }

    public static Optional<Direction> getDirectionByShortName(String shortName) {
        return Arrays.stream(values()).filter(o -> o.getShortName().equals(shortName)).findFirst();
    }

    public static boolean contains(final Direction[] array, Orientation orientation) {
        for (Direction direction : array) {
            if (direction.getOrientation().equals(orientation))
                return true;
        }
        return false;
    }
}
