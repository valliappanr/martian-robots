package uk.co.meenasoft.martian.model.action.movement;

import uk.co.meenasoft.martian.model.layout.Point;
import uk.co.meenasoft.martian.model.layout.Position;

public abstract class Movement  {

    protected Position incrementX(Position position) {
        Point currentPoint = position.getPoint();
        Point newPoint = new Point(currentPoint.getX() + 1, currentPoint.getY());
        return new Position(newPoint, position.getDirection());
    }

    protected Position incrementY(Position position) {
        Point currentPoint = position.getPoint();
        Point newPoint = new Point(currentPoint.getX() , currentPoint.getY() +1);
        return new Position(newPoint, position.getDirection());
    }

    protected Position decrementX(Position position) {
        Point currentPoint = position.getPoint();
        Point newPoint = new Point(currentPoint.getX() -1 , currentPoint.getY());
        return new Position(newPoint, position.getDirection());
    }

    protected Position decrementY(Position position) {
        Point currentPoint = position.getPoint();
        Point newPoint = new Point(currentPoint.getX() , currentPoint.getY() -1);
        return new Position(newPoint, position.getDirection());
    }

    abstract Position move(Position position);

}
