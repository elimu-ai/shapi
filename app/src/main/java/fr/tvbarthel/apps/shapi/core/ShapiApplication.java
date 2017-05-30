package fr.tvbarthel.apps.shapi.core;

import android.app.Application;

/**
 * Application used to build the app component and the whole graph.
 */
public class ShapiApplication extends Application {

    private static ApplicationComponent applicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = buildDaggerGraph();
    }

    /**
     * Access to the application component.
     *
     * @return application component.
     */
    public static ApplicationComponent component() {
        return applicationComponent;
    }

    /**
     * Initialize the dagger graph.
     *
     * @return application component build form dagger graph.
     */
    private ApplicationComponent buildDaggerGraph() {
        return DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
