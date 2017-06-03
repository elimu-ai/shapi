package fr.tvbarthel.apps.shapi.game;

import fr.tvbarthel.apps.shapi.shape.Shape;

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

        /**
         * Must register a drag listener which accept all kinds of {@link Shape}.
         *
         * @param view view on which the drag listener must be registered.
         */
        void registerDragListener(android.view.View view);

        /**
         * Must unregister a drag listener.
         *
         * @param view view for which the drag listener must be un registered.
         */
        void unregisterDragListener(android.view.View view);
    }

    /**
     * Define the contract of every view used to render a drop zone
     */
    interface View {

        /**
         * Called when a shaped has been dropped inside the drop zone.
         *
         * @param shape dropped zone.
         */
        void displayShapeDropped(Shape shape);
    }
}
