package fr.tvbarthel.apps.shapi.shape.identification;

import java.util.ArrayList;
import java.util.List;

import fr.tvbarthel.apps.shapi.event.EventTracker;
import fr.tvbarthel.apps.shapi.shape.Shape;

/**
 * A {@link Shape} identifier.
 */
public class ShapeIdentifier {

    private final EventTracker eventTracker;
    private final List<Shape> shapeCorrectlyIdentified;
    private final List<Shape> shapeIncorrectlyIdentified;

    /**
     * Construct a {@link ShapeIdentifier}
     *
     * @param eventTracker an {@link EventTracker}.
     */
    public ShapeIdentifier(EventTracker eventTracker) {
        this.eventTracker = eventTracker;
        shapeCorrectlyIdentified = new ArrayList<>();
        shapeIncorrectlyIdentified = new ArrayList<>();
    }

    /**
     * Identify a {@link Shape}.
     *
     * @param shape  a {@link Shape} to identify.
     * @param classz a {@link Class} of {@link Shape}.
     * @return true if the shape is assignable to the given class, false otherwise.
     */
    public boolean identify(Shape shape, Class<? extends Shape> classz) {
        if (classz.isAssignableFrom(shape.getClass())) {
            eventTracker.trackShapeCorrectlyIdentified(shape);
            shapeCorrectlyIdentified.add(shape);
            return true;
        } else {
            shapeIncorrectlyIdentified.add(shape);
            return false;
        }
    }

    /**
     * Get the {@link List} of {@link Shape} that were correctly identified.
     *
     * @return a {@link List} of {@link Shape}.
     */
    public List<Shape> getCorrectlyIdentifiedShapes() {
        return new ArrayList<>(shapeCorrectlyIdentified);
    }

    /**
     * Get the {@link List} of {@link Shape} that were incorrectly identified.
     *
     * @return a {@link List} of {@link Shape}.
     */
    public List<Shape> getIncorrectlyIdentifiedShapes() {
        return new ArrayList<>(shapeIncorrectlyIdentified);
    }

    /**
     * Reset this {@link ShapeIdentifier} to its
     * initial state.
     */
    public void reset() {
        shapeCorrectlyIdentified.clear();
        shapeIncorrectlyIdentified.clear();
    }
}
