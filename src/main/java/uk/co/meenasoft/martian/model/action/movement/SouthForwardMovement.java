package uk.co.meenasoft.martian.model.action.movement;

import uk.co.meenasoft.martian.model.layout.Position;

public class SouthForwardMovement extends Movement {
    private static final SouthForwardMovement INSTANCE = new SouthForwardMovement();
    public static final SouthForwardMovement getInstance() {
        return INSTANCE;
    }


    @Override
    public Position move(Position position) {
        return decrementY(position);
    }
    
}
