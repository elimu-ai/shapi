package fr.tvbarthel.apps.shapi.shape.generation;

import java.util.Random;

import fr.tvbarthel.apps.shapi.game.GameLevels;
import fr.tvbarthel.apps.shapi.shape.Circle;
import fr.tvbarthel.apps.shapi.shape.Diamond;
import fr.tvbarthel.apps.shapi.shape.Rectangle;
import fr.tvbarthel.apps.shapi.shape.Shape;
import fr.tvbarthel.apps.shapi.shape.Triangle;

/**
 * A {@link ShapeGenerator} that internally uses a {@link Random}
 * to generate random {@link fr.tvbarthel.apps.shapi.shape.Shape}s
 */
class RandomShapeGenerator implements ShapeGenerator {

    private final Random random;
    private final float shapeBorderWidth;

    /**
     * Create a {@link RandomShapeGenerator}.
     *
     * @param random           the {@link Random} that will be used internally to generate shapes.
     * @param shapeBorderWidth the width of the shape border.
     */
    RandomShapeGenerator(Random random, float shapeBorderWidth) {
        this.random = random;
        this.shapeBorderWidth = shapeBorderWidth;
    }

    @Override
    public Shape generate(@GameLevels.Level int gameLevel) {
        final int index = random.nextInt(4);
        return generateShape(index, gameLevel);
    }

    private Shape generateShape(int i, @GameLevels.Level int gameLevel) {
        switch (i) {
            case 0:
                return Rectangle.create(gameLevel, shapeBorderWidth);

            case 1:
                return Triangle.create(gameLevel, shapeBorderWidth);

            case 2:
                return Circle.create(gameLevel, shapeBorderWidth);

            case 3:
                return Diamond.create(gameLevel, shapeBorderWidth);

            default:
                throw new IllegalStateException("Unsupported index. Index must be in range [0;3]");
        }
    }

}
