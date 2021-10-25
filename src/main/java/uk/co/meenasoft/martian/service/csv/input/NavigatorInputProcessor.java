package uk.co.meenasoft.martian.service.csv.input;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.meenasoft.martian.exception.InvalidInputException;
import uk.co.meenasoft.martian.model.action.Instruction;
import uk.co.meenasoft.martian.model.input.NavigatorInput;
import uk.co.meenasoft.martian.model.input.RobotInput;
import uk.co.meenasoft.martian.model.layout.Direction;
import uk.co.meenasoft.martian.model.layout.Point;
import uk.co.meenasoft.martian.model.layout.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NavigatorInputProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(NavigatorInputProcessor.class.getName());
    private static final int MAX_INSTRUCTION_LENGTH = 100;
    private static final  String FILE_NOT_FOUND_ERROR_MSG = "Not found : %s";
    public static final String MAXIMUM_INSTRUCTIONS_ERROR_MSG = "Maximum instructions that can be processed is: %s";
    public static final String INVALID_ROBOT_DIRECTION_ERROR_MSG = "Robot Direction is invalid";
    public static final String ROBOT_POSITION_ERROR_MSG = "Robot Position should be x,y, direction";
    public static final String INVALID_INSTRUCTION_ERROR_MSG = "Invalid instructions %s for Robot";
    public static final String INVALID_ENDPOINT_COORDINATES_ERROR_MSG = "Co-ordinates of end point is invalid";
    public static final String CO_ORDINATES_EXCEEDING_MAXIMUM_VALUE_ERROR_MSG = "Co-ordinates exceeding maximum value(%s)";
    public static final int MAX_COORDINATE_VALUE = 50;
    public static final String CO_ORDINATES_OF_POINT_IS_NOT_A_NUMBER_ERROR_MSG = "Co-ordinates of point is not a number";

    public NavigatorInput retrieveNavigatorInput(String filePath) throws IOException, InvalidInputException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new FileNotFoundException(String.format(FILE_NOT_FOUND_ERROR_MSG, filePath));
        }
            List<String> contents = FileUtils.readLines(new File(filePath), Charset.defaultCharset());
            return extractNavigatorInput(contents);
    }


    public NavigatorInput extractNavigatorInput(List<String> contents) throws InvalidInputException {
        List<String> contentsWithoutEmptyLines = contents.stream().filter(line -> !StringUtils.isEmpty(line)).collect(Collectors.toList());;
        LOG.trace("Total lines after removing empty lines: {}", contentsWithoutEmptyLines.size());
        String layoutEndPointData = contentsWithoutEmptyLines.remove(0);
        Point layoutEndPoint = extractEndPoint(layoutEndPointData);
        LOG.debug("Layout endpoint {}", layoutEndPoint);
        List<RobotInput>  robotInputs = extractRobotInputs(contentsWithoutEmptyLines);
        robotInputs.stream().forEach(robotInput -> LOG.trace("Robot Input: {}", robotInput));
        LOG.trace("Total robot inputs: {}", robotInputs.size());
        return new NavigatorInput(layoutEndPoint, robotInputs);
    }

    List<RobotInput> extractRobotInputs(List<String> contentsWithoutEmptyLines) {
        List<List<String>> subList = ListUtils.partition(contentsWithoutEmptyLines, 2);
        return subList.stream().map(robotInputData -> {
            try {
                return extractRobotInput(robotInputData);
            } catch (InvalidInputException e) {
                LOG.error("Exception processing : {}", e.getMessage());
                throw new IllegalArgumentException(e.getMessage());
            }
        }).collect(Collectors.toList());
    }

    RobotInput extractRobotInput(List<String> input) throws InvalidInputException {
        String robotPositionInfo = input.get(0);
        String[] robotPositionData = validateAndGetRobotPositionData(robotPositionInfo);
        Point point = extractPoint(robotPositionData[0], robotPositionData[1]);
        Direction direction = retrieveDirectionFromRobotPosition(robotPositionData);
        List<Instruction> instructions = retrieveInstructions(input.get(1));
        return new RobotInput(new Position(point, direction), instructions);
    }

    List<Instruction> retrieveInstructions(String instructionsData) throws InvalidInputException {
        checkForInValidInstructions(instructionsData);
        if (instructionsData.length() > MAX_INSTRUCTION_LENGTH) {
            throw new InvalidInputException(String.format(MAXIMUM_INSTRUCTIONS_ERROR_MSG,
                    MAX_INSTRUCTION_LENGTH));
        }
        return instructionsData.chars()
                .mapToObj(c -> (char) c)
                .map(c -> Instruction.getInstructionByShortName(String.valueOf(c)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    Direction retrieveDirectionFromRobotPosition(String[] robotPositionData) throws InvalidInputException {
        Optional<Direction> optionalDirection = Direction.getDirectionByShortName(robotPositionData[2]);
        if (!optionalDirection.isPresent()) {
            LOG.error("Robot direction is invalid : {}", robotPositionData[2]);
            throw new InvalidInputException(INVALID_ROBOT_DIRECTION_ERROR_MSG);
        }
        return optionalDirection.get();
    }

    String[] validateAndGetRobotPositionData(String robotPositionInfo) throws InvalidInputException {
        String[] robotPositionData = robotPositionInfo.trim().split(" ");
        if (robotPositionData.length != 3) {
            LOG.error("Robot position data should be with three elements (x,y, direction: {})", robotPositionInfo);
            throw new InvalidInputException(ROBOT_POSITION_ERROR_MSG);
        }
        return robotPositionData;
    }

    void checkForInValidInstructions(String instructionsData) throws InvalidInputException {
        String invalidInstructions = instructionsData.chars()
                .mapToObj(c -> String.valueOf((char)c))
                .map(ins -> {
                    Optional<Instruction> optionalInstruction =
                            Instruction.getInstructionByShortName(ins);
                    return optionalInstruction.isPresent() ? StringUtils.EMPTY : ins;
                }).collect(Collectors.joining());
        if (!StringUtils.isEmpty(invalidInstructions)) {
            LOG.error("Invalid instructions for Robot: {}", invalidInstructions);
            throw new InvalidInputException(String.format(INVALID_INSTRUCTION_ERROR_MSG, invalidInstructions));
        }
    }

    Point extractEndPoint(String data) throws InvalidInputException {
        String[] coOrdinates = data.trim().split(" ");
        if (coOrdinates.length != 2) {
            LOG.error("Endpoint co-ordinates not containing exactly 2 elements : {}", data);
            throw new InvalidInputException(INVALID_ENDPOINT_COORDINATES_ERROR_MSG);
        }
        return extractPoint(coOrdinates[0], coOrdinates[1]);
    }

    Point extractPoint(String xData, String yData) throws InvalidInputException {
        try {
            int x = Integer.valueOf(xData);
            int y = Integer.valueOf(yData);
            if (x > MAX_COORDINATE_VALUE || y > MAX_COORDINATE_VALUE) {
                LOG.error("One of the co-ordinate data exceeding the boundary : x: {}, y:{}", x, y);
                throw new InvalidInputException(
                        String.format(CO_ORDINATES_EXCEEDING_MAXIMUM_VALUE_ERROR_MSG, MAX_COORDINATE_VALUE));
            }
            return new Point(x, y);
        }catch (NumberFormatException nex) {
            LOG.error("One of the Co-ordinate is not a number: x: {}, y: {}", xData, yData);
            throw new InvalidInputException(CO_ORDINATES_OF_POINT_IS_NOT_A_NUMBER_ERROR_MSG);
        }
    }
}