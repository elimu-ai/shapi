package fr.tvbarthel.apps.shapi.game;

import fr.tvbarthel.apps.shapi.shape.Shape;

/**
 * Implementation for kids.
 */
class EnginePresenterKidImpl implements GameContract.Presenter {

    private final GameEngine gameEngine;
    private GameContract.View view;

    /**
     * Create an {@link EnginePresenterKidImpl}
     *
     * @param gameEngine a {@link GameEngine}.
     */
    EnginePresenterKidImpl(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
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
        boolean correct = gameEngine.identifyCurrentShape(zone.getShape());
        showAnswer(correct);
        showCurrentScore();
    }

    @Override
    public void nextRound() {
        showCurrentShapeToIdentify();
        showDropZones();
    }

    @Override
    public void reset() {
        gameEngine.reset();
    }

    private void showCurrentShapeToIdentify() {
        view.displayShape(gameEngine.getCurrentShapeToIdentify(), true);
    }

    private void showCurrentScore() {
        final GameScore currentScore = gameEngine.getCurrentScore();
        view.displayScore(currentScore.getNumberOfCorrectAnswers(), currentScore.getNumberOfIncorrectAnswers());
    }

    private void showDropZones() {
        view.displayField(gameEngine.getCurrentField());
    }

    private void showAnswer(boolean correct) {
        if (view != null) {
            if (correct) {
                view.displayRightAnswer();
            } else {
                view.displayWrongAnswer();
            }
        }
    }
}
