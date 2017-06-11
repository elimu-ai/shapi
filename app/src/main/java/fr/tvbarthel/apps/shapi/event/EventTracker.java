package fr.tvbarthel.apps.shapi.event;

import fr.tvbarthel.apps.shapi.shape.Shape;

/**
 * An event tracker.
 */
public interface EventTracker {

    /**
     * Track a {@link Shape} that have been correctly identified.
     *
     * @param shape the {@link Shape} that has been correctly identified.
     */
    void trackShapeCorrectlyIdentified(Shape shape);
}
