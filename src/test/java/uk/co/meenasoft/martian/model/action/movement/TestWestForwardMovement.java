package uk.co.meenasoft.martian.model.action.movement;

import org.junit.jupiter.api.Test;
import uk.co.meenasoft.martian.model.Robot;
import uk.co.meenasoft.martian.model.layout.Direction;
import uk.co.meenasoft.martian.model.layout.Point;
import uk.co.meenasoft.martian.model.layout.Position;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static uk.co.meenasoft.martian.TestUtils.createLayout;
import static uk.co.meenasoft.martian.TestUtils.createRobot;

public class TestWestForwardMovement {

    @Test
    public void testForwardMovementActionShouldIncrementXaxisForWestForwardMovement() {
        ForwardMovementAction forwardMovementAction = ForwardMovementAction.getInstance();
        Robot robot = createRobot(new Point(1,1), Direction.WEST);
        forwardMovementAction.act(createLayout(), robot);
        Position expectedPosition = new Position(new Point(0,1), Direction.WEST);
        assertThat(robot.getPosition(), is(equalTo(expectedPosition)));
    }

}
