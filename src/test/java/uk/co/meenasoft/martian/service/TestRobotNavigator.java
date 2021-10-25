package uk.co.meenasoft.martian.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uk.co.meenasoft.martian.exception.InvalidInputException;
import uk.co.meenasoft.martian.model.*;
import uk.co.meenasoft.martian.model.input.NavigatorInput;
import uk.co.meenasoft.martian.model.layout.Direction;
import uk.co.meenasoft.martian.model.layout.Layout;
import uk.co.meenasoft.martian.model.layout.Point;
import uk.co.meenasoft.martian.model.layout.Position;
import uk.co.meenasoft.martian.service.csv.input.NavigatorInputProcessor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TestRobotNavigator {

    public static final String[] INSTRUCTIONS_CYCLIC = {"R", "F", "R", "F", "R", "F", "R", "F"};
    public static final List<String> INSTRUTIONS_FOR_MARKED_SCENTS = Arrays.asList("L", "L", "F", "F", "F", "L", "F", "L", "F", "L");
    public static final List<String> INSTRUCTIONS_LOST_STATUS = Arrays.asList("F", "R", "R", "F", "L", "L", "F", "F", "R", "R", "F", "L", "L");
    public static final List<String> INSTRUCTIONS_FOR_MARKED_SCENTS_WITH_OFF = Arrays.asList("O", "L", "L", "F", "F", "F", "L", "F", "L", "F", "L");
    private static RobotNavigator robotNavigator;

    @BeforeAll
    public static void setup() {
        Layout layout = new Layout(new Point(0,0), new Point(5,3));
        robotNavigator = new RobotNavigator(layout);
    }

    @Test
    public void testRobotNavigationOnACycleShouldComeToStartingPoint() {

        Robot robot = createRobot(1,1, Direction.EAST);
        robotNavigator.navigateByShortNames(robot, Arrays.asList(INSTRUCTIONS_CYCLIC));
        Robot expectedRobotState = createRobot(1,1, Direction.EAST);
        assertThat(robot, is(equalTo(expectedRobotState)));
    }

    @Test
    public void testRobotNavigatorShouldBeInLostStatusOnInstructionPathOutsideOfLayout() {
        Robot robot = createRobot(3,2, Direction.NORTH);
        robotNavigator.navigateByShortNames(robot, INSTRUCTIONS_LOST_STATUS);
        Robot expectedRobotState = createRobot(3,3, Direction.NORTH, true);
        assertThat(robot, is(equalTo(expectedRobotState)));
    }


    @Test
    public void testRobotNavigatorShouldUsePreviouslyMarkedScentsDuringNavigation() {
        Robot firstRobot = createRobot(3,2, Direction.NORTH);
        robotNavigator.navigateByShortNames(firstRobot, INSTRUCTIONS_LOST_STATUS);
        Robot secondRobot = createRobot(0,3, Direction.WEST);
        robotNavigator.navigateByShortNames(secondRobot, INSTRUTIONS_FOR_MARKED_SCENTS);
        Robot expectedRobotState = createRobot(2,3,Direction.SOUTH);
        assertThat(secondRobot, is(equalTo(expectedRobotState)));
    }

    @Test
    public void testRobotNavigatorShouldUsePreviouslyMarkedScentsDuringNavigationWithOFFInstruction() {
        Robot firstRobot = createRobot(3,2, Direction.NORTH);
        robotNavigator.navigateByShortNames(firstRobot, INSTRUCTIONS_LOST_STATUS);
        Robot secondRobot = createRobot(0,3, Direction.WEST);
        robotNavigator.navigateByShortNames(secondRobot, INSTRUCTIONS_FOR_MARKED_SCENTS_WITH_OFF);
        Robot expectedRobotState = createRobot(3,3,Direction.NORTH, true, true);
        assertThat(secondRobot, is(equalTo(expectedRobotState)));
    }

    @Test
    public void testRobotNavigatorShouldNavigateCorrectlyForValidInputFile() throws URISyntaxException, InvalidInputException, IOException {
        NavigatorInputProcessor navigatorInputProcessor = new NavigatorInputProcessor();
        NavigatorInput navigatorInput = navigatorInputProcessor.retrieveNavigatorInput(getAbsolutePath("validInput_1_robot_ins.txt"));
        Layout layout = new Layout(new Point(0,0), navigatorInput.getLayoutEndPoint());
        RobotNavigator robotNavigator = new RobotNavigator(layout);
        List<Robot> navigatedRobots = robotNavigator.createAndNavigate(navigatorInput.getRobotInputList());
        assertThat(navigatedRobots.size(), is(equalTo(1)));
        Robot expectedRobot = createRobot(1,1, Direction.SOUTH);
        assertThat(navigatedRobots.get(0), is(equalTo(expectedRobot)));
    }


    private String getAbsolutePath(String path) throws URISyntaxException {
        return Paths.get(getClass().getClassLoader().getResource(path).toURI()).toString();
    }


    private Robot createRobot(int x, int y, Direction direction ) {
        return createRobot(x,y, direction,false, false);

    }

    private Robot createRobot(int x, int y, Direction direction, boolean lostStatus ) {
        return createRobot(x,y, direction,lostStatus, false);

    }

    private Robot createRobot(int x, int y, Direction direction, boolean lostStatus, boolean scentCheckOff ) {
        Point robotPoint = new Point(x, y);
        Position robotPosition = new Position(robotPoint, direction);
        return new Robot(robotPosition, lostStatus, scentCheckOff);

    }

}
