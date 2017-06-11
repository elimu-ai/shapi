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

    @GameLevels.Level
    private final int gameLevel;

    /**
     * Encapsulate data linked to the game field.
     *
     * @param zones           drop zone which must be displayed on the field.
     * @param availableShapes available shapes type which can spawn on the field.
     * @param gameLevel       the {@link fr.tvbarthel.apps.shapi.game.GameLevels.Level}
     */
    public Field(List<DropZone> zones, List<Class<? extends Shape>> availableShapes, int gameLevel) {
        this.zones = zones;
        this.availableShapes = availableShapes;
        this.gameLevel = gameLevel;
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

    /**
     * Get the {@link fr.tvbarthel.apps.shapi.game.GameLevels.Level}.
     *
     * @return a {@link fr.tvbarthel.apps.shapi.game.GameLevels.Level}.
     */
    @GameLevels.Level
    public int getGameLevel() {
        return gameLevel;
    }
}
