package fr.tvbarthel.apps.shapi.game;

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
    GameContract.Presenter provideGamePresenter(GameEngine gameEngine) {
        List<DropZone> dropZones = new ArrayList<>();
        dropZones.add(new DropZone(Rectangle.class));
        dropZones.add(new DropZone(Triangle.class));
        dropZones.add(new DropZone(Circle.class));
        dropZones.add(new DropZone(Diamond.class));
        Collections.shuffle(dropZones);

        return new EnginePresenterKidImpl(gameEngine,
                dropZones.toArray(new DropZone[dropZones.size()]));
    }
}
