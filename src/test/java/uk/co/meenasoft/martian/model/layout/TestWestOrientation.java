package uk.co.meenasoft.martian.model.layout;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestWestOrientation {
    @Test
    public void testWestOrientationShouldCorrectOrientationForTurnLeft() {
        WestOrientation westOrientation = WestOrientation.getInstance();
        assertThat(westOrientation.turnLeft(), is(equalTo(Direction.SOUTH)));
    }

    @Test
    public void testWestOrientationShouldCorrectOrientationForTurnRight() {
        WestOrientation westOrientation = WestOrientation.getInstance();
        assertThat(westOrientation.turnRight(), is(equalTo(Direction.NORTH)));
    }
    
}
