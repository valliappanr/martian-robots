package uk.co.meenasoft.martian.model.action;

import uk.co.meenasoft.martian.model.Robot;
import uk.co.meenasoft.martian.model.layout.Layout;

public class OffMovementAction implements Action {
    private static final OffMovementAction INSTANCE = new OffMovementAction();
    public static OffMovementAction getInstance() {
        return INSTANCE;
    }

    @Override
    public void act(Layout layout, Robot robot) {
        robot.setScentCheckOff(true);
    }
}
