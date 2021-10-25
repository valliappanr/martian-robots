package uk.co.meenasoft.martian.model.layout;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestLayout {

    @Test
    public void testValidatePositionWithInBoundaryShouldBeSuccessfulValidForValidBoundary() {
        Layout layout = createLayout();
        boolean validationStatus = layout.validatePositionWithInBoundary(new Point(2,2));
        assertThat(validationStatus, is(equalTo(true)));
    }

    @Test
    public void testValidatePositionWithInBoundaryShouldBeUnSuccessfulForValidForValidBoundary() {
        Layout layout = createLayout();
        boolean validationStatus = layout.validatePositionWithInBoundary(new Point(4,2));
        assertThat(validationStatus, is(equalTo(false)));
    }

    @Test
    public void testIsPointInScentsShouldIndicateIfPointInScents() {
        Layout layout = createLayout();
        layout.getScents().add(new Point(4,2));
        boolean inScents = layout.isPointInScents(new Point(4,2));
        assertThat(inScents, is(equalTo(true)));

    }

    @Test
    public void testIsPointInScentsShouldNotIndicateIfPointNotInScents() {
        Layout layout = createLayout();
        layout.getScents().add(new Point(4,2));
        boolean inScents = layout.isPointInScents(new Point(2,2));
        assertThat(inScents, is(equalTo(false)));

    }

    private Layout createLayout() {
        return new Layout(new Point(0,0), new Point(3,3));
    }
}
