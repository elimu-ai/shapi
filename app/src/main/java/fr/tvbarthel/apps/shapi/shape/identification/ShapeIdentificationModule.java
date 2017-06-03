package fr.tvbarthel.apps.shapi.shape.identification;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * A {@link Module} for the shape identification package
 */
@Module
public class ShapeIdentificationModule {

    @Provides
    @Singleton
    ShapeIdentifier provideShapeIdentifier() {
        return new ShapeIdentifier();
    }
}
