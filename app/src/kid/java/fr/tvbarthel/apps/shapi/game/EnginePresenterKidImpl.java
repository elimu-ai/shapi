package fr.tvbarthel.apps.shapi.game;

import android.support.annotation.Size;
import android.view.View;

import fr.tvbarthel.apps.shapi.game.drag.DragManager;
import fr.tvbarthel.apps.shapi.shape.Shape;

/**
 * Implementation for kids.
 */
class EnginePresenterKidImpl implements GameContract.Presenter {

    private final GameEngine gameEngine;
    private final DropZone[] dropZones;
    private final DragManager dragManager;
    private GameContract.View view;

    /**
     * Create an {@link EnginePresenterKidImpl}
     *
     * @param gameEngine  a {@link GameEngine}
     * @param dropZones   an array of 4 {@link DropZone}s.
     * @param dragManager manager used to perform drag motion.
     */
    EnginePresenterKidImpl(GameEngine gameEngine,
                           @Size(4) DropZone[] dropZones,
                           DragManager dragManager) {
        this.gameEngine = gameEngine;
        this.dropZones = dropZones;
        this.dragManager = dragManager;
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

    @Override
    public void startDrag(View view, View.DragShadowBuilder shadowBuilder, Shape shape) {
        dragManager.startDrag(view, shadowBuilder, shape);
    }

    private void showCurrentShapeToIdentify() {
        view.displayShape(gameEngine.getCurrentShapeToIdentify(), true);
    }

    private void hideCurrentShapeToIdentify() {
        if (this.view != null) {
            this.view.displayShape(gameEngine.getCurrentShapeToIdentify(), false);
        }
    }

    private void showCurrentScore() {
        final GameScore currentScore = gameEngine.getCurrentScore();
        view.displayScore(currentScore.getNumberOfCorrectAnswers(), currentScore.getNumberOfIncorrectAnswers());
    }

    private void showDropZones() {
        view.displayDropZones(dropZones);
    }
}
