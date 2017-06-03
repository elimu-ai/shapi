package fr.tvbarthel.apps.shapi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import fr.tvbarthel.apps.shapi.core.ShapiApplication;
import fr.tvbarthel.apps.shapi.game.DropZone;
import fr.tvbarthel.apps.shapi.game.DropZoneView;
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
        implements GameContract.View, DropZoneView.Listener, ShapeView.Listener {

    /**
     * Dummy injection example.
     */
    @Inject
    protected GameContract.Presenter gamePresenter;

    private TextView score;
    private ShapeView shapeView;

    private DropZoneView dropZone1;
    private DropZoneView dropZone2;
    private DropZoneView dropZone3;
    private DropZoneView dropZone4;

    private Shape playedShape;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = ((TextView) findViewById(R.id.activity_main_score));
        shapeView = ((ShapeView) findViewById(R.id.activity_main_shape));
        shapeView.setListener(this);

        dropZone1 = ((DropZoneView) findViewById(R.id.activity_main_drop_zone_1));
        dropZone2 = ((DropZoneView) findViewById(R.id.activity_main_drop_zone_2));
        dropZone3 = ((DropZoneView) findViewById(R.id.activity_main_drop_zone_3));
        dropZone4 = ((DropZoneView) findViewById(R.id.activity_main_drop_zone_4));

        dropZone1.setListener(this);
        dropZone2.setListener(this);
        dropZone3.setListener(this);
        dropZone4.setListener(this);

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
    }

    @Override
    public void displayScore(int right, int wrong) {
        score.setText("Score: " + right + " | " + wrong);
    }

    @Override
    public void displayDropZones(@Size(4) DropZone[] zones) {
        dropZone1.setDropZone(zones[0]);
        dropZone2.setDropZone(zones[1]);
        dropZone3.setDropZone(zones[2]);
        dropZone4.setDropZone(zones[3]);
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
    public void onStartDragMotionRequested(ShapeView shapeView, View.DragShadowBuilder shadowBuilder) {
        gamePresenter.startDrag(shapeView, shadowBuilder, shapeView.getShape());
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
