package fr.tvbarthel.apps.shapi.game;

import android.support.annotation.NonNull;

import java.util.ArrayList;
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
                                 AudioEffectEngine audioEffectEngine,
                                 FieldGenerator fieldGenerator) {
        return new GameEngine(shapeIdentifier, shapeGenerator, audioEffectEngine, fieldGenerator);
    }

    @Provides
    @Singleton
    GameContract.Presenter provideGamePresenter(GameEngine gameEngine) {
        return new EnginePresenterKidImpl(gameEngine);
    }

    @Provides
    @Singleton
    FieldGenerator provideFieldGenerator(List<Class<? extends Shape>> availableShapes) {
        return new FieldGenerator(availableShapes);
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
