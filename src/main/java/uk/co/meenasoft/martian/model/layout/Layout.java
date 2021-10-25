package uk.co.meenasoft.martian.model.layout;

import lombok.AllArgsConstructor;
import lombok.Data;
import uk.co.meenasoft.martian.model.action.Action;
import uk.co.meenasoft.martian.model.action.Instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class Layout {
    private final Point startPoint;
    private final Point endPoint;
    private final Map<String, Action> instructionMap = Instruction.convertToMap();
    private final List<Point> scents = new ArrayList<>();

    public boolean validatePositionWithInBoundary(Point currentPoint) {
        int currentX = currentPoint.getX();
        int currentY = currentPoint.getY();
        int startX = startPoint.getX();
        int startY = startPoint.getY();
        int endX = endPoint.getX();
        int endY = endPoint.getY();

        if (startX > currentX || startY > currentY || endX < currentX || endY < currentY) {
            return false;
        }
        return true;
    }

    public boolean isPointInScents(Point currentPoint) {
        return scents.stream().filter(point -> {
            int scentX = point.getX();
            int scentY = point.getY();
            int currentX = currentPoint.getX();
            int currentY = currentPoint.getY();
            if (scentX == currentX && scentY == currentY) {
                return true;
            }
            return false;
        }).findFirst().isPresent();

    }
}
