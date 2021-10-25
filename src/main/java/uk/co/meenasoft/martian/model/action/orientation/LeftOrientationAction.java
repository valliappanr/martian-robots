package uk.co.meenasoft.martian.model.action.orientation;

import uk.co.meenasoft.martian.model.Robot;
import uk.co.meenasoft.martian.model.layout.Layout;
import uk.co.meenasoft.martian.model.layout.Position;
import uk.co.meenasoft.martian.model.action.Action;

public final class LeftOrientationAction extends OrientationAction {

    private static final LeftOrientationAction INSTANCE = new LeftOrientationAction();

    public static Action getInstance() {
        return INSTANCE;
    }

    private LeftOrientationAction() {

    }

    @Override
    public Position makeTurn(Layout layout, Robot robot) {
        Position position = robot.getPosition();
        return  turnLeft(position);
    }
}
