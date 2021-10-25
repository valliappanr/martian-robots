package uk.co.meenasoft.martian.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import uk.co.meenasoft.martian.model.action.Instruction;
import uk.co.meenasoft.martian.model.layout.Position;

import java.util.List;

@Data
@AllArgsConstructor
public class RobotInput {
    private final Position startPosition;
    private final List<Instruction> instructions;
}
