package uk.co.meenasoft.martian.model.layout;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {
    private Point point;
    private Direction direction;
}
