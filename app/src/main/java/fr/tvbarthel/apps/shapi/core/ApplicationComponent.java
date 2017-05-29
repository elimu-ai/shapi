package fr.tvbarthel.apps.shapi.core;

import javax.inject.Singleton;

import dagger.Component;
import fr.tvbarthel.apps.shapi.MainActivity;
import fr.tvbarthel.apps.shapi.engine.EngineModule;

/**
 * Component whose lifetime is going to be the lifetime of the application.
 */

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {
        ApplicationModule.class,
        EngineModule.class
})
public interface ApplicationComponent {

    /**
     * Field injections of any dependencies of the app
     *
     * @param activity object for which dependencies must be satisfied.
     */
    void inject(MainActivity activity);
}
