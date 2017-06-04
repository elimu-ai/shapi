package fr.tvbarthel.apps.shapi.game;

import fr.tvbarthel.apps.shapi.shape.Shape;

/**
 * Define the engine contract.
 */
public interface GameContract {

    /**
     * Define the business logic of a game.
     */
    interface Presenter {

        /**
         * Must be called when a new {@link GameContract.View} is attached to the presenter.
         * See also: {@link GameContract.Presenter#detachView(View)}
         * <p>
         * Right place to register internal listener.
         *
         * @param view view to which the presenter is attached.
         */
        void attachView(View view);

        /**
         * Must be called when previous attached {@link GameContract.View} is detached from the presenter.
         * See also: {@link GameContract.Presenter#detachView(View)}
         * <p>
         * Right place to unregister internal listener.
         *
         * @param view view from which the presenter is detached.
         */
        void detachView(View view);

        /**
         * Must initialize the game engine at start spawning shape.
         * <p>
         * See also:
         * {@link View#displayField(DropZone[])}
         * {@link View#displayShape(Shape, boolean)}
         */
        void start();

        /**
         * Must stop the game engine and destroy every internal components.
         */
        void stop();

        /**
         * Called when the user drop a shape inside a given drop zone.
         * <p>
         * Must compute the score for the given play and spawn a new shape if the identification
         * was successful or respawn the same shape if the identification was wrong.
         *
         * @param zone  zone where the user drop the shape.
         * @param shape shape dropped by the user.
         */
        void computeScore(DropZone zone, Shape shape);
    }

    /**
     * Define the contract of every view used to render a Game to the user.
     */
    interface View {

        /**
         * Called when the score must be displayed to the user.
         *
         * @param right number of right answers.
         * @param wrong number of wrong answers.
         */
        void displayScore(int right, int wrong);

        /**
         * Called when the game field must be displayed to the user.
         *
         * @param field game field to display.
         */
        void displayField(Field field);

        /**
         * Called when a new shape has just spawned and must be displayed to the user.
         *
         * @param shape new spawned shape to display.
         * @param shown true if the shape must be shown to the user, false if the shape must be hidden.
         */
        void displayShape(Shape shape, boolean shown);
    }
}
