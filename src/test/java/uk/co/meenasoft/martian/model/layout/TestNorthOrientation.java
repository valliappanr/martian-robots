package uk.co.meenasoft.martian.model.layout;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestNorthOrientation {
    @Test
    public void testNorthOrientationShouldCorrectOrientationForTurnLeft() {
        NorthOrientation northOrientation = NorthOrientation.getInstance();
        assertThat(northOrientation.turnLeft(), is(equalTo(Direction.WEST)));
    }

    @Test
    public void testNorthOrientationShouldCorrectOrientationForTurnRight() {
        NorthOrientation northOrientation = NorthOrientation.getInstance();
        assertThat(northOrientation.turnRight(), is(equalTo(Direction.EAST)));
    }

}
