package fr.tvbarthel.apps.shapi.shape.generation;

import fr.tvbarthel.apps.shapi.shape.Shape;

/**
 * A generator of {@link fr.tvbarthel.apps.shapi.shape.Shape}s.
 */
public interface ShapeGenerator {

    /**
     * Generate a new {@link Shape}.
     *
     * @return the newly generated {@link Shape}.
     */
    Shape generate();
}
