package fr.tvbarthel.apps.shapi.engine;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Module used to provide every component used by the engine.
 */
@Module
public class EngineModule {

    /**
     * Provide an implementation of the {@link GameContract.Presenter}
     *
     * @return implementation.
     */
    @Provides
    @Singleton
    GameContract.Presenter provideGamePresenter() {
        return new EnginePresenterKidImpl();
    }
}
