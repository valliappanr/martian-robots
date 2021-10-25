package uk.co.meenasoft.martian.model.action;

import org.junit.jupiter.api.Test;
import uk.co.meenasoft.martian.model.Robot;
import uk.co.meenasoft.martian.model.layout.Direction;
import uk.co.meenasoft.martian.model.layout.Point;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static uk.co.meenasoft.martian.TestUtils.createLayout;
import static uk.co.meenasoft.martian.TestUtils.createRobot;

public class TestOffMovementAction {

    @Test
    public void testOffMovementActionShouldUpdateRobotStateCorrectly() {
        OffMovementAction offMovementAction = OffMovementAction.getInstance();
        Robot robot = createRobot(new Point(1,1), Direction.EAST);
        offMovementAction.act(createLayout(), robot);
        assertThat(robot.isScentCheckOff(), is(equalTo(true)));
    }
}
