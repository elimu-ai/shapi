package fr.tvbarthel.apps.shapi.game;

import java.util.ArrayList;
import java.util.List;

import fr.tvbarthel.apps.shapi.shape.Shape;

/**
 * Encapsulate data linked to the game field.
 */
public class Field {

    private final List<DropZone> zones;

    private final List<Class<? extends Shape>> availableShapes;

    /**
     * Encapsulate data linked to the game field.
     *
     * @param zones           drop zone which must be displayed on the field.
     * @param availableShapes available shapes type which can spawn on the field.
     */
    public Field(List<DropZone> zones, List<Class<? extends Shape>> availableShapes) {
        this.zones = zones;
        this.availableShapes = availableShapes;
    }

    /**
     * drop zone which must be displayed on the field.
     *
     * @return drop zone which must be displayed on the field.
     */
    public List<DropZone> getZones() {
        return new ArrayList<>(zones);
    }

    /**
     * available shapes type which can spawn on the field.
     *
     * @return available shapes type which can spawn on the field.
     */
    public List<Class<? extends Shape>> getAvailableShapes() {
        return availableShapes;
    }
}
