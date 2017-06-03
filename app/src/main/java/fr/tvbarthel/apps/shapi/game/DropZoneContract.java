package fr.tvbarthel.apps.shapi.game;

import java.util.ArrayList;

/**
 * Define the common contract of area used to drop {@link fr.tvbarthel.apps.shapi.shape.Shape}
 */
interface DropZoneContract {

    /**
     * Define the business logic of a drop zone.
     */
    interface Presenter {

        /**
         * Must be called when a new {@link DropZoneContract.View} is attached to the presenter.
         * See also: {@link DropZoneContract.Presenter#detachView(View)}
         * <p>
         * Right place to register internal listener.
         *
         * @param view view to which the presenter is attached.
         */
        void attachView(View view);

        /**
         * Must be called when previous attached {@link DropZoneContract.View} is detached from the presenter.
         * See also: {@link DropZoneContract.Presenter#detachView(View)}
         * <p>
         * Right place to unregister internal listener.
         *
         * @param view view from which the presenter is detached.
         */
        void detachView(View view);
    }

    /**
     * Define the contract of every view used to render a drop zone
     */
    interface View {

        /**
         * Called when the view must register a drag listener in order to catch drag event from
         * every available shapes.
         *
         * @param availableShapes shapes available in the game.
         */
        void registerDragListener(ArrayList<Class<?>> availableShapes);

        /**
         * Called when the view must stop listening for drag events.
         */
        void unRegisterDragListener();
    }
}
