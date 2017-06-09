package fr.tvbarthel.apps.shapi.shape.generation;

import java.util.Random;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.tvbarthel.apps.shapi.R;
import fr.tvbarthel.apps.shapi.core.ShapiApplication;

/**
 * A {@link Module} for the shape generation package
 */
@Module
public class ShapeGenerationModule {

    @Provides
    @Singleton
    ShapeGenerator provideShapeGenerator(ShapiApplication shapiApplication) {
        final Random random = new Random();
        final float shapeBorderWidth = shapiApplication.getResources()
                .getDimension(R.dimen.shape_border_width);
        return new RandomShapeGenerator(random, shapeBorderWidth);
    }
}
