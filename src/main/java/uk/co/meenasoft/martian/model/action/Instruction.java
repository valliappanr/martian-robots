package uk.co.meenasoft.martian.model.action;

import uk.co.meenasoft.martian.model.action.movement.ForwardMovementAction;
import uk.co.meenasoft.martian.model.action.orientation.LeftOrientationAction;
import uk.co.meenasoft.martian.model.action.orientation.RightOrientationAction;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Instruction {
    RIGHT("R", RightOrientationAction.getInstance()),
    LEFT("L", LeftOrientationAction.getInstance()),
    FORWARD("F", ForwardMovementAction.getInstance()),
    OFF("O", OffMovementAction.getInstance());

    private final String shortName;
    private final Action action;

    Instruction(String shortName, Action action) {
        this.shortName = shortName;
        this.action = action;
    }

    public String getShortName() {
        return shortName;
    }

    public Action getAction() {
        return action;
    }
    public static Map<String, Action> convertToMap() {
        return Arrays.stream(values()).collect(Collectors.toMap(value -> value.getShortName(),
                value -> value.getAction()));
    }
    public static Optional<Instruction> getInstructionByShortName(String shortName) {
        Optional<Instruction> optionalInstruction =
                Arrays.stream(values()).filter(o -> o.getShortName().equals(shortName)).findFirst();
        return optionalInstruction;
    }
}
