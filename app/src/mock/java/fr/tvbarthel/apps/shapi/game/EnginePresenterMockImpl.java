package fr.tvbarthel.apps.shapi.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import fr.tvbarthel.apps.shapi.shape.Circle;
import fr.tvbarthel.apps.shapi.shape.Diamond;
import fr.tvbarthel.apps.shapi.shape.Rectangle;
import fr.tvbarthel.apps.shapi.shape.Shape;
import fr.tvbarthel.apps.shapi.shape.Triangle;

/**
 * Implementation completely mocked.
 */
class EnginePresenterMockImpl implements GameContract.Presenter {

    private final ArrayList<Shape> shapes;
    private final Random random;
    private final Field gameField;

    private GameContract.View view;
    private boolean playing;
    private int rightAnswer;
    private int wrongAnswer;
    private Shape currentPlayedShape;

    /**
     * Implementation completely mocked.
     */
    EnginePresenterMockImpl() {
        List<Class<? extends Shape>> availableShapes = new ArrayList<>();
        availableShapes.add(Rectangle.class);
        availableShapes.add(Triangle.class);
        availableShapes.add(Circle.class);
        availableShapes.add(Diamond.class);

        @GameLevels.Level
        final int level = GameLevels.LEVEL_1;

        shapes = new ArrayList<>();
        shapes.add(Rectangle.create(level, 10));
        shapes.add(Triangle.create(level, 10));
        shapes.add(Circle.create(level, 10));
        shapes.add(Diamond.create(level, 10));

        ArrayList<DropZone> dropZones = new ArrayList<>();
        dropZones.add(new DropZone(Rectangle.class, availableShapes, level));
        dropZones.add(new DropZone(Triangle.class, availableShapes, level));
        dropZones.add(new DropZone(Circle.class, availableShapes, level));
        dropZones.add(new DropZone(Diamond.class, availableShapes, level));

        gameField = new Field(dropZones, availableShapes, GameLevels.LEVEL_4);

        random = new Random(System.currentTimeMillis());
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
        boolean correct = zone.getShape().isInstance(shape);
        updateScore(correct);
        showAnswer(correct);
        generateNextShape(correct, shape);
    }

    @Override
    public void nextRound() {
        updateGameView();
    }

    @Override
    public void reset() {

    }

    private void initializeGame() {
        initializeShapes();
        initializeField();
        initializeScore();
        currentPlayedShape = shapes.get(random.nextInt(4));

    }

    private void initializeScore() {
        rightAnswer = 0;
        wrongAnswer = 0;
    }

    private void initializeShapes() {
        Collections.shuffle(shapes);
    }

    private void initializeField() {
        if (view != null) {
            view.displayField(gameField);
        }
    }

    private void updateGameView() {
        if (view != null) {
            view.displayScore(rightAnswer, wrongAnswer);
            view.displayShape(currentPlayedShape, true);
        }
    }

    private void generateNextShape(boolean correct, Shape shape) {
        if (correct) {
            currentPlayedShape = shapes.get(random.nextInt(4));
        } else {
            currentPlayedShape = shape;
        }
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

    private void updateScore(boolean correct) {
        if (correct) {
            rightAnswer++;
        } else {
            wrongAnswer++;
        }
    }
}
