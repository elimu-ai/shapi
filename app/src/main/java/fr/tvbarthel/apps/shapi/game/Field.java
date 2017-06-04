package fr.tvbarthel.apps.shapi.game;

import android.support.annotation.Size;

import java.util.List;

/**
 * Encapsulate data linked to the game field.
 */
public class Field {

    @Size(4)
    private final DropZone[] zones;

    private final List<Class<?>> availableShapes;

    /**
     * Encapsulate data linked to the game field.
     *
     * @param zones           drop zone which must be displayed on the field.
     * @param availableShapes available shapes type which can spawn on the field.
     */
    public Field(DropZone[] zones, List<Class<?>> availableShapes) {
        this.zones = zones;
        this.availableShapes = availableShapes;
    }

    /**
     * drop zone which must be displayed on the field.
     *
     * @return drop zone which must be displayed on the field.
     */
    public DropZone[] getZones() {
        return zones;
    }

    /**
     * available shapes type which can spawn on the field.
     *
     * @return available shapes type which can spawn on the field.
     */
    public List<Class<?>> getAvailableShapes() {
        return availableShapes;
    }
}
