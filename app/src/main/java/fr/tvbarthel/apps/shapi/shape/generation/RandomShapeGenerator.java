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

    /**
     * Create a {@link RandomShapeGenerator}.
     *
     * @param random the {@link Random} that will be used internally to generate shapes.
     */
    RandomShapeGenerator(Random random) {
        this.random = random;
    }

    @Override
    public Shape generate() {
        final int index = random.nextInt(4);
        return generateShape(index);
    }

    private Shape generateShape(int i) {
        switch (i) {
            case 0:
                return new Rectangle();

            case 1:
                return new Triangle();

            case 2:
                return new Circle();

            case 3:
                return new Diamond();

            default:
                throw new IllegalStateException("Unsupported index. Index must be in range [0;3]");
        }
    }

}
