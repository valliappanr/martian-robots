package uk.co.meenasoft.martian.model.layout;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEastOrientation {
    @Test
    public void testEastOrientationShouldCorrectOrientationForTurnLeft() {
        EastOrientation eastOrientation = EastOrientation.getInstance();
        assertThat(eastOrientation.turnLeft(), is(equalTo(Direction.NORTH)));
    }

    @Test
    public void testEastOrientationShouldCorrectOrientationForTurnRight() {
        EastOrientation eastOrientation = EastOrientation.getInstance();
        assertThat(eastOrientation.turnRight(), is(equalTo(Direction.SOUTH)));
    }
    
}
