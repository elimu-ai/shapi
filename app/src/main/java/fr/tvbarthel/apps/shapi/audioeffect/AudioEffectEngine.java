package fr.tvbarthel.apps.shapi.audioeffect;


/**
 * An engine responsible for playing audio effect.
 *
 * See {@link AudioEffects}
 */
public interface AudioEffectEngine {

    /**
     * Play an audio effect associated with the given
     * {@link fr.tvbarthel.apps.shapi.audioeffect.AudioEffects.AudioEffectId}
     *
     * @param audioEffectId a {@link fr.tvbarthel.apps.shapi.audioeffect.AudioEffects.AudioEffectId}
     */
    void play(@AudioEffects.AudioEffectId int audioEffectId);

    /**
     * Release this {@link AudioEffectEngine} and all it's internal resources.
     * <p>
     * <b>Note:</b> after releasing a {@link AudioEffectEngine}, it can
     * no longer be used and the reference should be set to null.
     */
    void release();

}
