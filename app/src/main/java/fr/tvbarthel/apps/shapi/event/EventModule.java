package fr.tvbarthel.apps.shapi.event;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.tvbarthel.apps.shapi.core.ShapiApplication;


/**
 * The {@link Module} for the event package.
 */
@Module
public class EventModule {

    @Provides
    @Singleton
    EventTracker provideEventTracker(ShapiApplication application) {
        return new LiteracyAppEventTracker(application);
    }
}
