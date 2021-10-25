package uk.co.meenasoft.martian.model.action.movement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.meenasoft.martian.model.Robot;
import uk.co.meenasoft.martian.model.action.Action;
import uk.co.meenasoft.martian.model.layout.*;

public class ForwardMovementAction implements Action {

    private static final Logger LOG = LoggerFactory.getLogger(ForwardMovementAction.class.getName());

    private static final ForwardMovementAction INSTANCE = new ForwardMovementAction();
    public static ForwardMovementAction getInstance() {
        return INSTANCE;
    }

    private ForwardMovementAction() {

    }
    @Override
    public void act(Layout layout, Robot robot) {
        if (robot.isLostStatus()) {
            LOG.debug("Robot in lost status");
            return;
        }
        Position position = robot.getPosition();
        LOG.trace("Current Position {} ", robot.getPosition());
        Direction currentDirection = position.getDirection();
        Position newPosition = currentDirection.getMovement().move(position);
        LOG.trace("New Position {} after executing  action {} ", newPosition, this);
        validateAndUpdateNewPosition(layout, robot, newPosition);
    }

    private void validateAndUpdateNewPosition(Layout layout, Robot robot, Position newPosition) {
        boolean positionInScents = layout.isPointInScents(robot.getPosition().getPoint());
        boolean positionValid = layout.validatePositionWithInBoundary(newPosition.getPoint());
        boolean scentCheckOff = robot.isScentCheckOff();
        if (isUpdateStateToInvalidAndAddToScent(positionInScents, positionValid, scentCheckOff)) {
            LOG.debug("Position not in scent and not valid,setting robot lost status to true and adding to scent");
            layout.getScents().add(robot.getPosition().getPoint());
            robot.setLostStatus(true);
        } else if(isUpdateStateToInvalid(positionInScents, positionValid, scentCheckOff)) {
            LOG.debug("Position in scent and not valid,setting robot lost status to true");
            robot.setLostStatus(true);
        } else if (isUpdateStateWithPositionAndDirection(positionInScents, positionValid, scentCheckOff)) {
            LOG.debug("Updating Robot with new position {}", newPosition);
            robot.getPosition().setDirection(newPosition.getDirection());
            robot.getPosition().setPoint(newPosition.getPoint());
        } else if(!isSkipUpdateState(positionInScents, positionValid, scentCheckOff)) {
            throw new IllegalStateException("Invalid state");
        }
    }

    private boolean isSkipUpdateState(boolean positionInScents, boolean positionValid, boolean scentCheckOff) {
        if (positionInScents && !positionValid && !scentCheckOff) {
            return true;
        }
        return false;
    }

    private boolean isUpdateStateWithPositionAndDirection(boolean positionInScents, boolean positionValid, boolean scentCheckOff) {
        if (positionValid) {
            return true;
        }
        return false;
    }

    private boolean isUpdateStateToInvalid(boolean positionInScents, boolean positionValid, boolean scentCheckOff) {
        if (positionInScents && !positionValid && scentCheckOff) {
            return true;
        }
        return false;
    }

    private boolean isUpdateStateToInvalidAndAddToScent(boolean positionInScents, boolean positionValid, boolean scentCheckOff) {
        if (!positionInScents && !positionValid && !scentCheckOff) {
            return true;
        }
        return false;
    }
}
