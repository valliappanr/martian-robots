package uk.co.meenasoft.martian.model.action.movement;

import uk.co.meenasoft.martian.model.layout.Position;

public final class NorthForwardMovement extends  Movement {
    private static final NorthForwardMovement INSTANCE = new NorthForwardMovement();
    public static final NorthForwardMovement getInstance() {
        return INSTANCE;
    }


    @Override
    public Position move(Position position) {
        return incrementY(position);
    }
}
