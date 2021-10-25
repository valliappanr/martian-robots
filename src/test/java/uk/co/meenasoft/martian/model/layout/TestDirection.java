package uk.co.meenasoft.martian.model.layout;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestDirection {
    @Test
    public void testDirectionShouldReturnCorrectOrientationForOrientation() {
        EastOrientation eastOrientation = EastOrientation.getInstance();
        assertThat(Direction.getDirection(eastOrientation).get(), is(equalTo(Direction.EAST)));
    }
}
