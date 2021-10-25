package uk.co.meenasoft.martian.model.action.orientation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.meenasoft.martian.model.Robot;
import uk.co.meenasoft.martian.model.layout.Layout;
import uk.co.meenasoft.martian.model.layout.Orientation;
import uk.co.meenasoft.martian.model.layout.Position;
import uk.co.meenasoft.martian.model.action.Action;

public abstract class OrientationAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(OrientationAction.class.getName());
    protected Position turnRight(Position position) {
        Orientation currentOrientation = position.getDirection().getOrientation();
        return new Position(position.getPoint(), currentOrientation.turnRight());

    }

    protected Position turnLeft(Position position) {
        Orientation currentDirection = position.getDirection().getOrientation();
        return new Position(position.getPoint(), currentDirection.turnLeft());
    }

    @Override
    public void act(Layout layout, Robot robot) {
        LOG.trace("Current Position {} ", robot.getPosition());
        Position newPosition = makeTurn(layout, robot);
        LOG.trace("New Position {} after executing  action {} ", newPosition, this);
        robot.getPosition().setDirection(newPosition.getDirection());
    }
    public abstract Position makeTurn(Layout layout, Robot robot);
}
