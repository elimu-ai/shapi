package fr.tvbarthel.apps.shapi.game;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.tvbarthel.apps.shapi.game.drag.DragManager;
import fr.tvbarthel.apps.shapi.shape.Circle;
import fr.tvbarthel.apps.shapi.shape.Diamond;
import fr.tvbarthel.apps.shapi.shape.Rectangle;
import fr.tvbarthel.apps.shapi.shape.Shape;
import fr.tvbarthel.apps.shapi.shape.Triangle;

/**
 * Module used to provide every component used by the engine.
 */
@Module
public class EngineModule {

    /**
     * Provide an implementation of the {@link fr.tvbarthel.apps.shapi.game.GameContract.Presenter}
     *
     * @return implementation.
     */
    @Provides
    @Singleton
    GameContract.Presenter provideGamePresenter(@NonNull DragManager dragManager) {
        return new EnginePresenterMockImpl(dragManager);
    }

    @Provides
    DropZoneContract.Presenter provideDropZonePresenter(@NonNull DragManager dragManager,
                                                        @NonNull List<Class<? extends Shape>> shapes) {
        return new DropZonePresenterMockImpl(dragManager, shapes);
    }

    @Singleton
    @Provides
    @NonNull
    DragManager provideDragManager() {
        return DragManager.getInstance();
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
