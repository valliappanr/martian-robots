package uk.co.meenasoft.martian.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import uk.co.meenasoft.martian.model.layout.Position;

@Data
@AllArgsConstructor
public class Robot {
    private final Position position;
    private boolean lostStatus;
    private boolean scentCheckOff;
}
