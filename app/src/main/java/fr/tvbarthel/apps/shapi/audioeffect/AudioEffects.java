package fr.tvbarthel.apps.shapi.audioeffect;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Static fields for dealing with audio effects.
 */
public final class AudioEffects {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({AUDIO_EFFECT_ID_SUCCESS, AUDIO_EFFECT_ID_FAILURE})
    public @interface AudioEffectId {
    }

    /**
     * The id of an audio effect representing a success.
     */
    public static final int AUDIO_EFFECT_ID_SUCCESS = 1;

    /**
     * The id of an audio effect representing a failure.
     */
    public static final int AUDIO_EFFECT_ID_FAILURE = 2;

    private AudioEffects() {
        //no instance
    }
}
