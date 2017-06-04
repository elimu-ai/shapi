package fr.tvbarthel.apps.shapi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import fr.tvbarthel.apps.shapi.core.ShapiApplication;
import fr.tvbarthel.apps.shapi.game.DropZone;
import fr.tvbarthel.apps.shapi.game.DropZoneView;
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
        implements GameContract.View, DropZoneView.Listener, FieldView.Listener {

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
    private FieldView fieldView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = ((TextView) findViewById(R.id.activity_main_score));
        shapeView = ((ShapeView) findViewById(R.id.activity_main_shape));
        fieldView = ((FieldView) findViewById(R.id.activity_main_field_view));
        fieldView.setListener(this);

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
    public void displayField(Field field) {
        final List<DropZone> zones = field.getZones();
        displayDropZones(zones);
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

    private void displayDropZones(List<DropZone> zones) {
        if (zones.size() == 1) {
            dropZone1.setVisibility(View.VISIBLE);
            dropZone2.setVisibility(View.GONE);
            dropZone3.setVisibility(View.GONE);
            dropZone4.setVisibility(View.GONE);

            dropZone1.setDropZone(zones.get(0));
        } else if (zones.size() == 2) {
            dropZone1.setVisibility(View.VISIBLE);
            dropZone2.setVisibility(View.VISIBLE);
            dropZone3.setVisibility(View.GONE);
            dropZone4.setVisibility(View.GONE);

            dropZone1.setDropZone(zones.get(0));
            dropZone2.setDropZone(zones.get(1));
        } else if (zones.size() == 4) {
            dropZone1.setVisibility(View.VISIBLE);
            dropZone2.setVisibility(View.VISIBLE);
            dropZone3.setVisibility(View.VISIBLE);
            dropZone4.setVisibility(View.VISIBLE);

            dropZone1.setDropZone(zones.get(0));
            dropZone2.setDropZone(zones.get(1));
            dropZone3.setDropZone(zones.get(2));
            dropZone4.setDropZone(zones.get(3));
        }
    }

}
