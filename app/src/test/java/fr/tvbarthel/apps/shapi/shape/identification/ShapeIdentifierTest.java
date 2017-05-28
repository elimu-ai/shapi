package fr.tvbarthel.apps.shapi.shape.identification;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import fr.tvbarthel.apps.shapi.shape.Circle;
import fr.tvbarthel.apps.shapi.shape.Diamond;
import fr.tvbarthel.apps.shapi.shape.Rectangle;
import fr.tvbarthel.apps.shapi.shape.Shape;
import fr.tvbarthel.apps.shapi.shape.Triangle;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Test for the {@link ShapeIdentifier} class;
 */
public class ShapeIdentifierTest {


    private ShapeIdentifier shapeIdentifier;

    @Before
    public void setup() {
        shapeIdentifier = new ShapeIdentifier();
    }

    @Test
    public void identifyShapeCorrectly() {
        final Rectangle rectangle = new Rectangle();
        final boolean success = shapeIdentifier.identify(rectangle, Rectangle.class);
        assertTrue(success);
    }

    @Test
    public void identifyShapeIncorrectly() {
        final Triangle triangle = new Triangle();
        final boolean failure = shapeIdentifier.identify(triangle, Diamond.class);
        assertFalse(failure);
    }

    @Test
    public void whenIdentifyAddCorrectlyIdentifiedShape() {
        final Rectangle rectangle = new Rectangle();
        shapeIdentifier.identify(rectangle, Rectangle.class);

        final Circle circle = new Circle();
        shapeIdentifier.identify(circle, Circle.class);

        final List<Shape> correctlyIdentifiedShapes = shapeIdentifier.getCorrectlyIdentifiedShapes();
        assertEquals(2, correctlyIdentifiedShapes.size());
        assertEquals(rectangle, correctlyIdentifiedShapes.get(0));
        assertEquals(circle, correctlyIdentifiedShapes.get(1));
    }

    @Test
    public void whenIdentifyAddIncorrecltyIdentifiedShape() {
        final Rectangle rectangle = new Rectangle();
        shapeIdentifier.identify(rectangle, Diamond.class);

        final Diamond diamond = new Diamond();
        shapeIdentifier.identify(diamond, Triangle.class);

        final List<Shape> incorrectlyIdentifiedShapes = shapeIdentifier.getIncorrectlyIdentifiedShapes();
        assertEquals(2, incorrectlyIdentifiedShapes.size());
        assertEquals(rectangle, incorrectlyIdentifiedShapes.get(0));
        assertEquals(diamond, incorrectlyIdentifiedShapes.get(1));
    }

}
