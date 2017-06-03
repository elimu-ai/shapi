package fr.tvbarthel.apps.shapi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import fr.tvbarthel.apps.shapi.core.ShapiApplication;
import fr.tvbarthel.apps.shapi.engine.DropZone;
import fr.tvbarthel.apps.shapi.engine.GameContract;
import fr.tvbarthel.apps.shapi.shape.Shape;
import fr.tvbarthel.apps.shapi.shape.ShapeView;

/**
 * Main Activity.
 * <p>
 * Never rename or move package.
 * https://android-developers.googleblog.com/2011/06/things-that-cannot-change.html
 */
public class MainActivity extends AppCompatActivity implements GameContract.View, View.OnClickListener {

    /**
     * Dummy injection example.
     */
    @Inject
    protected GameContract.Presenter gamePresenter;

    private TextView score;
    private ShapeView shapeView;
    private DropZoneBoxView dropZone1;
    private DropZoneBoxView dropZone2;
    private DropZoneBoxView dropZone3;
    private DropZoneBoxView dropZone4;

    private Shape playedShape;
    private DropZone[] dropZones;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = ((TextView) findViewById(R.id.activity_main_score));
        shapeView = ((ShapeView) findViewById(R.id.activity_main_shape));

        dropZone1 = ((DropZoneBoxView) findViewById(R.id.activity_main_drop_zone_1));
        dropZone2 = ((DropZoneBoxView) findViewById(R.id.activity_main_drop_zone_2));
        dropZone3 = ((DropZoneBoxView) findViewById(R.id.activity_main_drop_zone_3));
        dropZone4 = ((DropZoneBoxView) findViewById(R.id.activity_main_drop_zone_4));

        dropZone1.setOnClickListener(this);
        dropZone2.setOnClickListener(this);
        dropZone3.setOnClickListener(this);
        dropZone4.setOnClickListener(this);

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
        dropZones = zones;
        dropZone1.setDropZone(zones[0]);
        dropZone2.setDropZone(zones[1]);
        dropZone3.setDropZone(zones[2]);
        dropZone4.setDropZone(zones[3]);
    }

    @Override
    public void displayShape(Shape shape) {
        playedShape = shape;
        shapeView.setShape(shape);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_drop_zone_1:
                gamePresenter.computeScore(dropZones[0], playedShape);
                break;
            case R.id.activity_main_drop_zone_2:
                gamePresenter.computeScore(dropZones[1], playedShape);
                break;
            case R.id.activity_main_drop_zone_3:
                gamePresenter.computeScore(dropZones[2], playedShape);
                break;
            case R.id.activity_main_drop_zone_4:
                gamePresenter.computeScore(dropZones[3], playedShape);
                break;
            default:
                throw new IllegalStateException("Click not handled: " + v);

        }
    }
}
