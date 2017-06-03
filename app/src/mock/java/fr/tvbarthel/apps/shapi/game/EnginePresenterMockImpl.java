package fr.tvbarthel.apps.shapi.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
    private final ArrayList<DropZone> dropZones;
    private final Random random;

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
        shapes.add(new Rectangle());
        shapes.add(new Triangle());
        shapes.add(new Circle());
        shapes.add(new Diamond());

        dropZones = new ArrayList<>();
        dropZones.add(new DropZone(Rectangle.class));
        dropZones.add(new DropZone(Triangle.class));
        dropZones.add(new DropZone(Circle.class));
        dropZones.add(new DropZone(Diamond.class));

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
        generateNextShape(correct, shape);
        updateGameView();
    }

    private void initializeGame() {
        initializeShapes();
        initializeDropZones();
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

    private void initializeDropZones() {
        Collections.shuffle(dropZones);
        if (view != null) {
            view.displayDropZones(dropZones.toArray(new DropZone[dropZones.size()]));
        }
    }

    private void updateGameView() {
        if (view != null) {
            view.displayScore(rightAnswer, wrongAnswer);
            view.displayShape(currentPlayedShape);
        }
    }

    private void generateNextShape(boolean correct, Shape shape) {
        if (correct) {
            currentPlayedShape = shapes.get(random.nextInt(4));
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
