package fr.tvbarthel.apps.shapi.game;

import android.support.annotation.Size;

import fr.tvbarthel.apps.shapi.shape.Shape;

/**
 * Implementation for kids.
 */
class EnginePresenterKidImpl implements GameContract.Presenter {

    private final GameEngine gameEngine;
    private final DropZone[] dropZones;
    private GameContract.View view;

    /**
     * Create an {@link EnginePresenterKidImpl}
     *
     * @param gameEngine a {@link GameEngine}
     * @param dropZones  an array of 4 {@link DropZone}s.
     */
    EnginePresenterKidImpl(GameEngine gameEngine,
                           @Size(4) DropZone[] dropZones) {
        this.gameEngine = gameEngine;
        this.dropZones = dropZones;
    }

    @Override
    public void attachView(GameContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView(GameContract.View view) {
        if (view == this.view) {
            this.view = null;
        }
    }

    @Override
    public void start() {
        showDropZones();
        showCurrentScore();
        showCurrentShapeToIdentify();
    }

    @Override
    public void stop() {

    }

    @Override
    public void computeScore(DropZone zone, Shape shape) {
        gameEngine.identifyCurrentShape(zone.getShape());
        showCurrentScore();
        showCurrentShapeToIdentify();
    }

    private void showCurrentShapeToIdentify() {
        view.displayShape(gameEngine.getCurrentShapeToIdentify(), true);
    }

    private void showCurrentScore() {
        final GameScore currentScore = gameEngine.getCurrentScore();
        view.displayScore(currentScore.getNumberOfCorrectAnswers(), currentScore.getNumberOfIncorrectAnswers());
    }

    private void showDropZones() {
        view.displayDropZones(dropZones);
    }
}
