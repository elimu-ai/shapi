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
     * Generate a new {@link Field} according to the
     * {@link fr.tvbarthel.apps.shapi.game.GameLevels.Level}.
     * <p>
     * Here are the specification of the {@link Field}
     * generated:
     * <p>
     * - for the {@link fr.tvbarthel.apps.shapi.game.GameLevels#LEVEL_1}: one drop zone
     * - for the {@link fr.tvbarthel.apps.shapi.game.GameLevels#LEVEL_2}: two drop zones
     * - for the {@link fr.tvbarthel.apps.shapi.game.GameLevels#LEVEL_3}: four drop zones
     * - for the {@link fr.tvbarthel.apps.shapi.game.GameLevels#LEVEL_4}: four drop zones
     *
     * @param currentShapeToIdentify the current {@link Shape} to identify.
     * @param level                  the {@link fr.tvbarthel.apps.shapi.game.GameLevels.Level}.
     * @return a newly created {@link Field}.
     */
    Field generateNewField(Shape currentShapeToIdentify, @GameLevels.Level int level) {
        switch (level) {
            case GameLevels.LEVEL_1:
                return generateFieldWithOneDropZone(currentShapeToIdentify, level);

            case GameLevels.LEVEL_2:
                return generateFieldWithTwoDropZones(currentShapeToIdentify, level);

            case GameLevels.LEVEL_3:
            case GameLevels.LEVEL_4:
                return generateFieldWithFourDropZones(currentShapeToIdentify, level);

            default:
                throw new IllegalArgumentException("Unsupported level. Found: " + level);
        }
    }

    @NonNull
    private Field generateFieldWithOneDropZone(Shape currentShapeToIdentify, int level) {
        final ArrayList<DropZone> dropZones = new ArrayList<>(1);
        dropZones.add(new DropZone(currentShapeToIdentify.getClass(), availableShapes));
        return new Field(dropZones, availableShapes, level);
    }

    @NonNull
    private Field generateFieldWithTwoDropZones(Shape currentShapeToIdentify, int level) {
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

        return new Field(dropZones, availableShapes, level);
    }

    private Field generateFieldWithFourDropZones(Shape currentShapeToIdentify, int level) {
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

        return new Field(dropZones, availableShapes, level);
    }


}
