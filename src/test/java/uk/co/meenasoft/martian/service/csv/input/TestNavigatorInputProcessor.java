package uk.co.meenasoft.martian.service.csv.input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.co.meenasoft.martian.exception.InvalidInputException;
import uk.co.meenasoft.martian.model.action.Instruction;
import uk.co.meenasoft.martian.model.input.NavigatorInput;
import uk.co.meenasoft.martian.model.input.RobotInput;
import uk.co.meenasoft.martian.model.layout.Direction;
import uk.co.meenasoft.martian.model.layout.Point;
import uk.co.meenasoft.martian.model.layout.Position;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestNavigatorInputProcessor {

    private static final NavigatorInputProcessor navigatorInputProcessor= new NavigatorInputProcessor();
    private static final String INSTRUCTIONS_SET_MORE_THAN_HUNDRED =
            "RFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRF";


    private String getAbsolutePath(String path) throws URISyntaxException {
        return Paths.get(getClass().getClassLoader().getResource(path).toURI()).toString();
    }

    @Test
    public void testExtractPointShouldThrowExceptionIfDataIsNotInteger() {
        String x = "10";
        String y = "a";
        Exception expectedException = Assertions.assertThrows(InvalidInputException.class, () -> {
            navigatorInputProcessor.extractPoint(x,y);
        });
        assertThat(expectedException.getMessage(), is(equalTo("Co-ordinates of point is not a number")));
    }

    @Test
    public void testExtractPointShouldThrowExceptionIfOneOfTheCoordinateMorethanFifty() {
        String x = "10";
        String y = "70";
        Exception expectedException = Assertions.assertThrows(InvalidInputException.class, () -> {
            navigatorInputProcessor.extractPoint(x,y);
        });
        assertThat(expectedException.getMessage(), is(equalTo("Co-ordinates exceeding maximum value(50)")));
    }

    @Test
    public void testExtractPointShouldThrowExceptionIfInputContainsLessThanTwoCoordinates() {
        String input = "10";
        Exception expectedException = Assertions.assertThrows(InvalidInputException.class, () -> {
            navigatorInputProcessor.extractEndPoint(input);
        });
        assertThat(expectedException.getMessage(), is(equalTo("Co-ordinates of end point is invalid")));
    }

    @Test
    public void testExtractPointShouldExtractEndPointCorrectlyForValidInput() throws InvalidInputException {
        String input = "10 40";
        Point point = navigatorInputProcessor.extractEndPoint(input);
        Point expectedPoint = new Point(10, 40);
        assertThat(point, is(equalTo(expectedPoint)));
    }

    @Test
    public void testCheckForInValidInstructionsShouldThrowExceptionForInvalidInstructions() {
        String input = "A";
        Exception expectedException = Assertions.assertThrows(InvalidInputException.class, () -> {
            navigatorInputProcessor.checkForInValidInstructions(input);
        });
        assertThat(expectedException.getMessage(), is(equalTo("Invalid instructions A for Robot")));
    }

    @Test
    public void testRetrieveInstructionsShouldFailIfMaximumInstructionsIsGreatherThanHundred() {
        Exception expectedException = Assertions.assertThrows(InvalidInputException.class, () -> {
            navigatorInputProcessor.retrieveInstructions(INSTRUCTIONS_SET_MORE_THAN_HUNDRED);
        });
        assertThat(expectedException.getMessage(),
                is(equalTo("Maximum instructions that can be processed is: 100")));

    }

    @Test
    public void testValidateAndGetRobotPositionDataShouldThrowExceptionIfPositionInfoIsInvalid() throws InvalidInputException {
        String input = "1 1 E";
        String[] validatedInput = navigatorInputProcessor.validateAndGetRobotPositionData(input);
        String[] expectedInput = new String[] {"1", "1", "E"};
        assertThat(expectedInput, is(equalTo(validatedInput)));
    }


    @Test
    public void testRetrieveNanavigatorInputProcessorvigatorInputShouldThrowFileNotFoundForNonExistingFile() throws IOException,  URISyntaxException {
        Exception expectedException = Assertions.assertThrows(FileNotFoundException.class, () -> {
            navigatorInputProcessor.retrieveNavigatorInput("filenotfound.txt");
        });
        assertThat(expectedException.getMessage(), is(equalTo("Not found : filenotfound.txt")));
    }



    @Test
    public void testRetrieveNavigatorInputShouldRetrieveNavigatorInputForValidFile() throws IOException, URISyntaxException, InvalidInputException {
        NavigatorInputProcessor navigatorInputProcessor = new NavigatorInputProcessor();
        NavigatorInput navigatorInput = navigatorInputProcessor.retrieveNavigatorInput(getAbsolutePath("validInput_1_robot_ins.txt"));
        Point layoutEndpoint = new Point(5,3);
        RobotInput robotInput= new RobotInput(new Position(new Point(1,1), Direction.EAST), Arrays.asList(Instruction.RIGHT));
        NavigatorInput expectedNavigatorInput = new NavigatorInput(layoutEndpoint, Arrays.asList(robotInput));
        assertThat(navigatorInput, is(equalTo(expectedNavigatorInput)));
    }

}
