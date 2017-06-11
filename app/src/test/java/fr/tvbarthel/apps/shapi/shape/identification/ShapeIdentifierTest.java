package fr.tvbarthel.apps.shapi.shape.identification;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import fr.tvbarthel.apps.shapi.event.EventTracker;
import fr.tvbarthel.apps.shapi.shape.Circle;
import fr.tvbarthel.apps.shapi.shape.Diamond;
import fr.tvbarthel.apps.shapi.shape.Rectangle;
import fr.tvbarthel.apps.shapi.shape.Shape;
import fr.tvbarthel.apps.shapi.shape.Triangle;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Test for the {@link ShapeIdentifier} class;
 */
public class ShapeIdentifierTest {


    @Mock
    private EventTracker eventTracker;
    private ShapeIdentifier shapeIdentifier;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        shapeIdentifier = new ShapeIdentifier(eventTracker);
    }

    @Test
    public void identifyShapeCorrectly() {
        final Rectangle rectangle = mock(Rectangle.class);
        final boolean success = shapeIdentifier.identify(rectangle, Rectangle.class);
        assertTrue(success);
    }

    @Test
    public void identifyShapeIncorrectly() {
        final Triangle triangle = mock(Triangle.class);
        final boolean failure = shapeIdentifier.identify(triangle, Diamond.class);
        assertFalse(failure);
    }

    @Test
    public void whenIdentifyAddCorrectlyIdentifiedShape() {
        final Rectangle rectangle = mock(Rectangle.class);
        shapeIdentifier.identify(rectangle, Rectangle.class);

        final Circle circle = mock(Circle.class);
        shapeIdentifier.identify(circle, Circle.class);

        final List<Shape> correctlyIdentifiedShapes = shapeIdentifier.getCorrectlyIdentifiedShapes();
        assertEquals(2, correctlyIdentifiedShapes.size());
        assertEquals(rectangle, correctlyIdentifiedShapes.get(0));
        assertEquals(circle, correctlyIdentifiedShapes.get(1));
    }

    @Test
    public void whenIdentifyAddIncorrecltyIdentifiedShape() {
        final Rectangle rectangle = mock(Rectangle.class);
        shapeIdentifier.identify(rectangle, Diamond.class);

        final Diamond diamond = mock(Diamond.class);
        shapeIdentifier.identify(diamond, Triangle.class);

        final List<Shape> incorrectlyIdentifiedShapes = shapeIdentifier.getIncorrectlyIdentifiedShapes();
        assertEquals(2, incorrectlyIdentifiedShapes.size());
        assertEquals(rectangle, incorrectlyIdentifiedShapes.get(0));
        assertEquals(diamond, incorrectlyIdentifiedShapes.get(1));
    }

    @Test
    public void whenIdentifyCorrectlyThenTrackEvent() {
        final Rectangle rectangle = mock(Rectangle.class);
        shapeIdentifier.identify(rectangle, Rectangle.class);

        verify(eventTracker).trackShapeCorrectlyIdentified(rectangle);
    }

    @Test
    public void whenIdentifyIncorrectlyThenDoNotTrackEvent() {
        final Rectangle rectangle = mock(Rectangle.class);
        shapeIdentifier.identify(rectangle, Triangle.class);

        verify(eventTracker, never()).trackShapeCorrectlyIdentified(rectangle);
    }

}
