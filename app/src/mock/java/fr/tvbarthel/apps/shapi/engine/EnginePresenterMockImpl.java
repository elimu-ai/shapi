package fr.tvbarthel.apps.shapi.engine;

import java.util.ArrayList;

import fr.tvbarthel.apps.shapi.shape.Circle;
import fr.tvbarthel.apps.shapi.shape.Diamond;
import fr.tvbarthel.apps.shapi.shape.Rectangle;
import fr.tvbarthel.apps.shapi.shape.Shape;
import fr.tvbarthel.apps.shapi.shape.Triangle;

/**
 * Implementation completely mocked.
 */
public class EnginePresenterMockImpl implements GameContract.Presenter {

    private final ArrayList<Shape> shapes;

    private GameContract.View view;
    private boolean playing;
    private int rightAnswer;
    private int wrongAnswer;
    private Shape currentPlayedShape;

    /**
     * Implementation completely mocked.
     */
    public EnginePresenterMockImpl() {
        shapes = new ArrayList<>();
    }

    @Override
    public void attachView(GameContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView(GameContract.View view) {
        this.view = null;
    }

    @Override
    public void start() {
        playing = true;
        initializeGame();
        updateGameView();
    }

    @Override
    public void stop() {
        playing = false;
    }

    @Override
    public void computeScore(DropZone zone, Shape shape) {
        boolean correct = !shapes.isEmpty();
        updateScore(correct);
        generateNextShape(correct, shape);
        updateGameView();
    }

    private void initializeGame() {
        shapes.add(new Rectangle());
        shapes.add(new Triangle());
        shapes.add(new Circle());
        shapes.add(new Diamond());

        rightAnswer = 0;
        wrongAnswer = 0;

        currentPlayedShape = shapes.remove(0);
    }

    private void updateGameView() {
        if (view != null) {
            view.displayScore(rightAnswer, wrongAnswer);
            view.displayShape(currentPlayedShape);
        }
    }

    private void generateNextShape(boolean correct, Shape shape) {
        if (correct) {
            currentPlayedShape = shapes.remove(0);
        } else {
            currentPlayedShape = shape;
        }
    }

    private void updateScore(boolean correct) {
        if (correct) {
            rightAnswer++;
        } else {
            wrongAnswer++;
        }
    }
}
