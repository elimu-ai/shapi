package fr.tvbarthel.apps.shapi.game;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.tvbarthel.apps.shapi.shape.Shape;

/**
 * A generator of {@link Field} that can creates
 * {@link Field} of different levels.
 */
class FieldGenerator {

    /**
     * The level 1 contains only 1 {@link DropZone}: the {@link DropZone}
     * of the current {@link Shape} to identify.
     */
    static final int LEVEL_1 = 1;

    /**
     * The level 2 contains 2 {@link DropZone}s: the {@link DropZone}
     * of the current {@link Shape} to identify and 1 extra {@link DropZone}
     * of another type.
     */
    static final int LEVEL_2 = 2;

    /**
     * The level 3 contains 4 {@link DropZone}s: the {@link DropZone}
     * of the current {@link Shape} to identify and 3 extra {@link DropZone}s
     * of other types.
     */
    static final int LEVEL_3 = 3;

    private final List<Class<? extends Shape>> availableShapes;

    /**
     * Create a {@link FieldGenerator} that will use
     * the available {@link Shape}s to get the extra {@link DropZone}s.
     *
     * @param availableShapes the available {@link Shape}s.
     */
    FieldGenerator(List<Class<? extends Shape>> availableShapes) {
        this.availableShapes = new ArrayList<>(availableShapes);
    }

    /**
     * Generate a new {@link Field}.
     * <p>
     * <b>See</b> {@link FieldGenerator#LEVEL_1}
     * <b>See</b> {@link FieldGenerator#LEVEL_2}
     * <b>See</b> {@link FieldGenerator#LEVEL_3}
     *
     * @param currentShapeToIdentify the current {@link Shape} to identify.
     * @param level                  the target level.
     * @return a newly created {@link Field}.
     */
    Field generateNewField(Shape currentShapeToIdentify, int level) {
        switch (level) {
            case LEVEL_1:
                return generateFieldLevel1(currentShapeToIdentify);

            case LEVEL_2:
                return generateFieldLevel2(currentShapeToIdentify);

            case LEVEL_3:
                return generateFieldLevel3(currentShapeToIdentify);

            default:
                throw new IllegalArgumentException("Unsupported level. Found: " + level);
        }
    }

    @NonNull
    private Field generateFieldLevel1(Shape currentShapeToIdentify) {
        final ArrayList<DropZone> dropZones = new ArrayList<>(1);
        dropZones.add(new DropZone(currentShapeToIdentify.getClass(), availableShapes));
        return new Field(dropZones, availableShapes);
    }

    @NonNull
    private Field generateFieldLevel2(Shape currentShapeToIdentify) {
        if (availableShapes.size() < 2) {
            throw new IllegalStateException("Must have at least 2 available shapes to create a field of level 2");
        }

        final Class<? extends Shape> currentShapeClass = currentShapeToIdentify.getClass();
        availableShapes.remove(currentShapeClass);
        Collections.shuffle(availableShapes);
        final Class<? extends Shape> candidate = availableShapes.get(0);
        availableShapes.add(currentShapeClass);

        final ArrayList<DropZone> dropZones = new ArrayList<>(2);
        dropZones.add(new DropZone(currentShapeClass, availableShapes));
        dropZones.add(new DropZone(candidate, availableShapes));

        Collections.shuffle(dropZones);

        return new Field(dropZones, availableShapes);
    }

    private Field generateFieldLevel3(Shape currentShapeToIdentify) {
        if (availableShapes.size() < 4) {
            throw new IllegalStateException("Must have at least 4 available shapes to create a field of level 2");
        }

        final Class<? extends Shape> currentShapeClass = currentShapeToIdentify.getClass();
        availableShapes.remove(currentShapeClass);
        Collections.shuffle(availableShapes);
        final Class<? extends Shape> candidate1 = availableShapes.get(0);
        final Class<? extends Shape> candidate2 = availableShapes.get(1);
        final Class<? extends Shape> candidate3 = availableShapes.get(2);
        availableShapes.add(currentShapeClass);

        final ArrayList<DropZone> dropZones = new ArrayList<>(4);
        dropZones.add(new DropZone(currentShapeClass, availableShapes));
        dropZones.add(new DropZone(candidate1, availableShapes));
        dropZones.add(new DropZone(candidate2, availableShapes));
        dropZones.add(new DropZone(candidate3, availableShapes));

        Collections.shuffle(dropZones);

        return new Field(dropZones, availableShapes);
    }


}
