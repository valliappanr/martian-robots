package uk.co.meenasoft.martian.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.meenasoft.martian.model.action.Instruction;
import uk.co.meenasoft.martian.model.input.RobotInput;
import uk.co.meenasoft.martian.model.layout.Layout;
import uk.co.meenasoft.martian.model.layout.Point;
import uk.co.meenasoft.martian.model.layout.Position;
import uk.co.meenasoft.martian.model.Robot;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RobotNavigator {
    private static final Logger LOG = LoggerFactory.getLogger(RobotNavigator.class.getName());
    private final Layout layout;

    public RobotNavigator(Layout layout) {
        this.layout = layout;
    }

    public void navigate(List<RobotInput> robotInputs) {
        List<Robot> navigatedRobots = createAndNavigate(robotInputs);
        printRobotStatus(navigatedRobots);
    }

    List<Robot> createAndNavigate(List<RobotInput> robotInputs) {
        return robotInputs.stream().map(robotInput -> {
            Robot robot = new Robot(robotInput.getStartPosition(), false, false);
            navigate(robot, robotInput.getInstructions());
            return robot;
        }).collect(Collectors.toList());
    }

    private void printRobotStatus(List<Robot> navigatedRobots) {
        String navigatedRobotsStatusOutput = navigatedRobots.stream().map(navigatedRobot -> {
            return robotOutput(navigatedRobot);
        }).collect(Collectors.joining(System.lineSeparator()));
        LOG.info("Robots final state after navigation:  {}{}", System.lineSeparator(), navigatedRobotsStatusOutput);
    }
    private String robotOutput(Robot robot) {
        Position robotPosition = robot.getPosition();
        Point robotPoint = robotPosition.getPoint();
        String lostStatusOutput = robot.isLostStatus() ? "LOST" : StringUtils.EMPTY;
        return String.format("%s %s %s %s", robotPoint.getX(), robotPoint.getY(), robotPosition.getDirection().getShortName(),
                lostStatusOutput);
    }

    public void navigateByShortNames(Robot robot, List<String> instructionShortNames) {
        List<Instruction> instructions = instructionShortNames.stream().map(instructionShortName -> {
            return Instruction.getInstructionByShortName(instructionShortName);
        }).filter(Optional::isPresent)
                .map(Optional::get).collect(Collectors.toList());
        navigate(robot, instructions);
    }

    public void navigate(Robot robot, List<Instruction> instructions) {
        LOG.debug("Navigating robot: {}", robot);
        instructions.stream().filter(instruction -> {
            LOG.debug("Processing instruction {} ", instruction);
            Position currentPosition = robot.getPosition();
            layout.getInstructionMap().get(instruction.getShortName()).act(layout, robot);
            return robot.isLostStatus();
        }).findFirst();
    }
}