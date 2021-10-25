package uk.co.meenasoft.martian.model.action.movement;

import uk.co.meenasoft.martian.model.layout.Position;

public class EastForwardMovement extends Movement {
    private static final EastForwardMovement INSTANCE = new EastForwardMovement();
    public static final EastForwardMovement getInstance() {
        return INSTANCE;
    }


    @Override
    public Position move(Position position) {
        return incrementX(position);
    }
    
}
