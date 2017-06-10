package fr.tvbarthel.apps.shapi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import javax.inject.Inject;

import fr.tvbarthel.apps.shapi.core.ShapiApplication;
import fr.tvbarthel.apps.shapi.game.DropZone;
import fr.tvbarthel.apps.shapi.game.Field;
import fr.tvbarthel.apps.shapi.game.FieldView;
import fr.tvbarthel.apps.shapi.game.GameContract;
import fr.tvbarthel.apps.shapi.shape.Shape;
import fr.tvbarthel.apps.shapi.shape.ShapeView;

/**
 * Main Activity.
 * <p>
 * Never rename or move package.
 * https://android-developers.googleblog.com/2011/06/things-that-cannot-change.html
 */
public class MainActivity extends AppCompatActivity
        implements GameContract.View, FieldView.Listener {

    /**
     * Dummy injection example.
     */
    @Inject
    protected GameContract.Presenter gamePresenter;

    private ShapeView shapeView;

    private Shape playedShape;
    private FieldView fieldView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shapeView = ((ShapeView) findViewById(R.id.activity_main_shape));
        fieldView = ((FieldView) findViewById(R.id.activity_main_field_view));
        fieldView.setListener(this);

        ShapiApplication.component().inject(this);
        gamePresenter.attachView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gamePresenter.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gamePresenter.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gamePresenter.detachView(this);

        if (!isChangingConfigurations()) {
            gamePresenter.reset();
        }
    }

    @Override
    public void displayScore(int right, int wrong) {

    }

    @Override
    public void displayField(Field field) {
        fieldView.setField(field);
    }

    @Override
    public void displayShape(Shape shape, boolean shown) {
        if (shown) {
            showShape(shape);
        } else {
            hideShape(shape);
        }
    }

    @Override
    public void onShapeDropped(@NonNull DropZone dropZone, @Nullable Shape shape) {
        gamePresenter.computeScore(dropZone, playedShape);
    }

    @Override
    public void onEmphasisOnAvailableActionRequested() {
        shapeView.animateActionIndicator();
    }

    private void hideShape(Shape shape) {
        playedShape = shape;
        shapeView.setVisibility(View.INVISIBLE);
    }

    private void showShape(Shape shape) {
        playedShape = shape;
        shapeView.setVisibility(View.VISIBLE);
        shapeView.setShape(shape);
    }

}
