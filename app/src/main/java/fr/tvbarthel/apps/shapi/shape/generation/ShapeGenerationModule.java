package fr.tvbarthel.apps.shapi.shape.generation;

import java.util.Random;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * A {@link Module} for the shape generation package
 */
@Module
public class ShapeGenerationModule {

    @Provides
    @Singleton
    ShapeGenerator provideShapeGenerator() {
        final Random random = new Random();
        return new RandomShapeGenerator(random);
    }
}
