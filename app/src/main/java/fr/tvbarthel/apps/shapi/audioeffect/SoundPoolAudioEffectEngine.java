package fr.tvbarthel.apps.shapi.audioeffect;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.NonNull;

import fr.tvbarthel.apps.shapi.R;

/**
 * An {@link AudioEffectEngine} using a {@link SoundPool} internally to play
 * the audio effects.
 */
class SoundPoolAudioEffectEngine implements AudioEffectEngine {

    private final SoundPool soundPool;
    private final int successSoundId;
    private final int failureSoundId;

    /**
     * Create a {@link SoundPoolAudioEffectEngine}.
     *
     * @param context a {@link Context}.
     */
    SoundPoolAudioEffectEngine(Context context) {
        soundPool = createSoundPool();
        successSoundId = soundPool.load(context, R.raw.success, 1);
        failureSoundId = soundPool.load(context, R.raw.failure, 1);
    }


    @Override
    public void play(@AudioEffects.AudioEffectId int audioEffectId) {
        if (audioEffectId == AudioEffects.AUDIO_EFFECT_ID_SUCCESS) {
            playInternal(successSoundId);
        } else if (audioEffectId == AudioEffects.AUDIO_EFFECT_ID_FAILURE) {
            playInternal(failureSoundId);
        } else {
            throw new IllegalArgumentException("Unsupported audio effect id. Found: " + audioEffectId);
        }
    }

    @Override
    public void release() {
        soundPool.release();
    }

    private void playInternal(int soundId) {
        soundPool.play(soundId, 1f, 1f, 0, 0, 1);
    }

    private SoundPool createSoundPool() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            return createSoundPoolLollipop();
        } else {
            return createSoundPoolBase();
        }
    }

    @SuppressWarnings("deprecation")
    @NonNull
    private SoundPool createSoundPoolBase() {
        return new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private SoundPool createSoundPoolLollipop() {
        final AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        return new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(4)
                .build();
    }
}
