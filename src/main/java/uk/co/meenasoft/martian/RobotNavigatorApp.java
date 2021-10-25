package uk.co.meenasoft.martian;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.meenasoft.martian.exception.InvalidInputException;
import uk.co.meenasoft.martian.model.input.NavigatorInput;
import uk.co.meenasoft.martian.model.input.RobotInput;
import uk.co.meenasoft.martian.model.layout.Layout;
import uk.co.meenasoft.martian.model.layout.Point;
import uk.co.meenasoft.martian.service.RobotNavigator;
import uk.co.meenasoft.martian.service.csv.input.NavigatorInputProcessor;

import java.io.IOException;
import java.util.List;

public class RobotNavigatorApp {
    private static final Logger LOG = LoggerFactory.getLogger(RobotNavigatorApp.class.getName());
    private static final Point LAYOUT_START_POINT = new Point(0,0);
    private NavigatorInputProcessor navigatorInputProcessor;
    public RobotNavigatorApp(NavigatorInputProcessor navigatorInputProcessor) {
        this.navigatorInputProcessor = navigatorInputProcessor;
    }
    public static void main(String[] args) throws InvalidInputException, IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Expecting input file");
        }
        String filePath = args[0];
        LOG.debug("File path to process: {}", filePath);
        RobotNavigatorApp robotNavigatorApp = new RobotNavigatorApp(new NavigatorInputProcessor());
        robotNavigatorApp.navigate(filePath);
    }
    public void navigate(String filePath) throws InvalidInputException, IOException {
        NavigatorInput navigatorInput = navigatorInputProcessor.retrieveNavigatorInput(filePath);
        Point startPoint = LAYOUT_START_POINT;
        Point endPoint = navigatorInput.getLayoutEndPoint();
        List<RobotInput> robotInputList = navigatorInput.getRobotInputList();
        LOG.debug("Board layout start point : {} endpoint: {}", startPoint, endPoint);
        LOG.debug("Total robots to navigate: {}", robotInputList.size());
        Layout layout = new Layout(startPoint, endPoint);
        RobotNavigator robotNavigator = new RobotNavigator(layout);
        robotNavigator.navigate(robotInputList);
    }
}
