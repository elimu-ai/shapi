package fr.tvbarthel.apps.shapi.shape.generation;

import fr.tvbarthel.apps.shapi.game.GameLevels;
import fr.tvbarthel.apps.shapi.shape.Shape;

/**
 * A generator of {@link fr.tvbarthel.apps.shapi.shape.Shape}s.
 */
public interface ShapeGenerator {

    /**
     * Generate a new {@link Shape}.
     *
     * @param gameLevel the {@link fr.tvbarthel.apps.shapi.game.GameLevels.Level}.
     * @return the newly generated {@link Shape}.
     */
    Shape generate(@GameLevels.Level int gameLevel);
}
