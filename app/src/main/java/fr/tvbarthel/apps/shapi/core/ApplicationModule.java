package fr.tvbarthel.apps.shapi.core;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Module used to provide any components linked to the
 * application.
 */
@Module
 class ApplicationModule {

    /**
     * Holding application.
     */
    private final ShapiApplication application;

    /**
     * Module used to provide any components linked to the
     * application.
     *
     * @param application holding application.
     */
     ApplicationModule(ShapiApplication application) {
        this.application = application;
    }

    /**
     * Provide the application for the graph.
     *
     * @return holding application.
     */
    @Provides
    @Singleton
     ShapiApplication provideApplication() {
        return application;
    }

    /**
     * Provide the context of the holding application for the graph.
     *
     * @return holding application context.
     */
    @Provides
    @Singleton
     Context provideContext() {
        return application.getApplicationContext();
    }
}
