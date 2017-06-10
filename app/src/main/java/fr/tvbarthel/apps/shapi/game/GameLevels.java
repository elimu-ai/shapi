package fr.tvbarthel.apps.shapi.game;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Game levels: the difficulty of the game
 * should increase with the game level.
 */
public final class GameLevels {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_4})
    public @interface Level {
    }

    /**
     * The level 1.
     */
    public static final int LEVEL_1 = 1;

    /**
     * The level 2.
     */
    public static final int LEVEL_2 = 2;

    /**
     * The level 3.
     */
    public static final int LEVEL_3 = 3;

    /**
     * The level 4.
     */
    public static final int LEVEL_4 = 4;

    private GameLevels() {
        //no instance
    }
}
