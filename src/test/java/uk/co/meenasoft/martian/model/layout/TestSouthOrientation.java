package uk.co.meenasoft.martian.model.layout;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSouthOrientation {
    @Test
    public void testSouthOrientationShouldCorrectOrientationForTurnLeft() {
        SouthOrientation southOrientation = SouthOrientation.getInstance();
        assertThat(southOrientation.turnLeft(), is(equalTo(Direction.EAST)));
    }

    @Test
    public void testSouthOrientationShouldCorrectOrientationForTurnRight() {
        SouthOrientation southOrientation = SouthOrientation.getInstance();
        assertThat(southOrientation.turnRight(), is(equalTo(Direction.WEST)));
    }
    
}
