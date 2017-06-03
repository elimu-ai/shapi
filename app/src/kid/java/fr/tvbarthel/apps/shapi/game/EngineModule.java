package fr.tvbarthel.apps.shapi.game;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.tvbarthel.apps.shapi.audioeffect.AudioEffectEngine;
import fr.tvbarthel.apps.shapi.shape.Circle;
import fr.tvbarthel.apps.shapi.shape.Diamond;
import fr.tvbarthel.apps.shapi.shape.Rectangle;
import fr.tvbarthel.apps.shapi.shape.Shape;
import fr.tvbarthel.apps.shapi.shape.Triangle;
import fr.tvbarthel.apps.shapi.shape.generation.ShapeGenerator;
import fr.tvbarthel.apps.shapi.shape.identification.ShapeIdentifier;

/**
 * Module used to provide every component used by the engine.
 */
@Module
public class EngineModule {

    @Provides
    @Singleton
    GameEngine provideGameEngine(ShapeGenerator shapeGenerator,
                                 ShapeIdentifier shapeIdentifier,
                                 AudioEffectEngine audioEffectEngine) {
        return new GameEngine(shapeIdentifier, shapeGenerator, audioEffectEngine);
    }

    @Provides
    @Singleton
    GameContract.Presenter provideGamePresenter(GameEngine gameEngine,
                                                @NonNull List<Class<? extends Shape>> shapes) {

        List<DropZone> dropZones = new ArrayList<>();
        for (Class<? extends Shape> shape : shapes) {
            dropZones.add(new DropZone(shape));
        }
        Collections.shuffle(dropZones);

        return new EnginePresenterKidImpl(
                gameEngine,
                dropZones.toArray(new DropZone[dropZones.size()])
        );
    }

    @Provides
    DropZoneContract.Presenter provideDropZonePresenter(@NonNull List<Class<? extends Shape>> shapes) {
        return new DropZonePresenterKidImpl(shapes);
    }

    @Provides
    @Singleton
    @NonNull
    List<Class<? extends Shape>> provideAvailableShapes() {
        List<Class<? extends Shape>> availableShapes = new ArrayList<>();
        availableShapes.add(Rectangle.class);
        availableShapes.add(Triangle.class);
        availableShapes.add(Circle.class);
        availableShapes.add(Diamond.class);
        return availableShapes;
    }
}
