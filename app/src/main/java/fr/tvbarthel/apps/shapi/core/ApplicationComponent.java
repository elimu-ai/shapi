package fr.tvbarthel.apps.shapi.core;

import javax.inject.Singleton;

import dagger.Component;
import fr.tvbarthel.apps.shapi.MainActivity;
import fr.tvbarthel.apps.shapi.audioeffect.AudioEffectModule;
import fr.tvbarthel.apps.shapi.event.EventModule;
import fr.tvbarthel.apps.shapi.game.DropZoneView;
import fr.tvbarthel.apps.shapi.game.EngineModule;
import fr.tvbarthel.apps.shapi.shape.generation.ShapeGenerationModule;
import fr.tvbarthel.apps.shapi.shape.identification.ShapeIdentificationModule;

/**
 * Component whose lifetime is going to be the lifetime of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {
        ApplicationModule.class,
        AudioEffectModule.class,
        EngineModule.class,
        EventModule.class,
        ShapeGenerationModule.class,
        ShapeIdentificationModule.class
})
public interface ApplicationComponent {

    /**
     * Field injections of any dependencies of the app
     *
     * @param activity object for which dependencies must be satisfied.
     */
    void inject(MainActivity activity);

    /**
     * Field injections of any dependencies of the app
     *
     * @param dropZoneView object for which dependencies must be satisfied.
     */
    void inject(DropZoneView dropZoneView);
}
