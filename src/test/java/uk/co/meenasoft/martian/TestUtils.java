package uk.co.meenasoft.martian;

import uk.co.meenasoft.martian.model.Robot;
import uk.co.meenasoft.martian.model.layout.Direction;
import uk.co.meenasoft.martian.model.layout.Layout;
import uk.co.meenasoft.martian.model.layout.Point;
import uk.co.meenasoft.martian.model.layout.Position;

public class TestUtils {
    public static Layout createLayout() {
        return new Layout(new Point(0,0), new Point(10, 10));
    }
    public static Robot createRobot() {
        return new Robot(new Position(new Point(1,1), Direction.EAST), false, false);
    }

    public static Robot createRobot(Point point, Direction direction) {
        return new Robot(new Position(point, direction), false, false);
    }


}
