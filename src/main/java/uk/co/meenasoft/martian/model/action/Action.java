package uk.co.meenasoft.martian.model.action;

import uk.co.meenasoft.martian.model.Robot;
import uk.co.meenasoft.martian.model.layout.Layout;
import uk.co.meenasoft.martian.model.layout.Position;

public interface Action {
    void act(Layout layout, Robot robot);
}
