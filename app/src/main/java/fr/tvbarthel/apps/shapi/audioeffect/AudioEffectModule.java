package fr.tvbarthel.apps.shapi.audioeffect;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.tvbarthel.apps.shapi.core.ShapiApplication;

/**
 * The {@link Module} of the audio effect package.
 */
@Module
public class AudioEffectModule {

    @Provides
    @Singleton
    AudioEffectEngine provideAudioEffectEngine(ShapiApplication application) {
        return new SoundPoolAudioEffectEngine(application);
    }
}
