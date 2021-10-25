package uk.co.meenasoft.martian.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import uk.co.meenasoft.martian.model.layout.Point;


import java.util.List;

@Data
@AllArgsConstructor
public class NavigatorInput {
    private final Point layoutEndPoint;
    private final List<RobotInput> robotInputList;
}
