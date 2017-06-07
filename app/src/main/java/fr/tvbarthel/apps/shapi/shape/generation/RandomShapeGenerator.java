package fr.tvbarthel.apps.shapi.shape.generation;

import java.util.Random;

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
     * @param random the {@link Random} that will be used internally to generate shapes.
     * @param shapeBorderWidth the width of the shape border.
     */
    RandomShapeGenerator(Random random, float shapeBorderWidth) {
        this.random = random;
        this.shapeBorderWidth = shapeBorderWidth;
    }

    @Override
    public Shape generate() {
        final int index = random.nextInt(4);
        return generateShape(index);
    }

    private Shape generateShape(int i) {
        switch (i) {
            case 0:
                return new Rectangle(shapeBorderWidth);

            case 1:
                return new Triangle(shapeBorderWidth);

            case 2:
                return new Circle(shapeBorderWidth);

            case 3:
                return new Diamond(shapeBorderWidth);

            default:
                throw new IllegalStateException("Unsupported index. Index must be in range [0;3]");
        }
    }

}
