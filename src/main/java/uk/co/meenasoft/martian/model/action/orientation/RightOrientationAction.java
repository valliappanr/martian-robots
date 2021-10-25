package uk.co.meenasoft.martian.model.action.orientation;

import uk.co.meenasoft.martian.model.Robot;
import uk.co.meenasoft.martian.model.layout.Layout;
import uk.co.meenasoft.martian.model.layout.Position;

public final class RightOrientationAction extends OrientationAction {
    private static final RightOrientationAction INSTANCE = new RightOrientationAction();

    public static RightOrientationAction getInstance() {
        return INSTANCE;
    }

    private RightOrientationAction() {

    }

    @Override
    public Position makeTurn(Layout layout, Robot robot)
    {
        Position currentPosition = robot.getPosition();
        return turnRight(currentPosition);
    }
}
