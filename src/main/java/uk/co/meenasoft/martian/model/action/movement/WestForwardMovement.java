package uk.co.meenasoft.martian.model.action.movement;

import uk.co.meenasoft.martian.model.layout.Position;

public class WestForwardMovement extends Movement {
    private static final WestForwardMovement INSTANCE = new WestForwardMovement();
    public static final WestForwardMovement getInstance() {
        return INSTANCE;
    }


    @Override
    public Position move(Position position) {
        return decrementX(position);
    }
    
}
