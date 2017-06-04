package fr.tvbarthel.apps.shapi.shape.identification;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.tvbarthel.apps.shapi.event.EventTracker;

/**
 * A {@link Module} for the shape identification package
 */
@Module
public class ShapeIdentificationModule {

    @Provides
    @Singleton
    ShapeIdentifier provideShapeIdentifier(EventTracker eventTracker) {
        return new ShapeIdentifier(eventTracker);
    }
}
