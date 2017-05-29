package fr.tvbarthel.apps.shapi;

import android.os.Bundle;
import android.support.annotation.Size;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import fr.tvbarthel.apps.shapi.core.ShapiApplication;
import fr.tvbarthel.apps.shapi.engine.DropZone;
import fr.tvbarthel.apps.shapi.engine.GameContract;
import fr.tvbarthel.apps.shapi.shape.Shape;

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
    private TextView shape;
    private Shape playedShape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = ((TextView) findViewById(R.id.activity_main_score));
        shape = ((TextView) findViewById(R.id.activity_main_shape));
        findViewById(R.id.activity_main_play).setOnClickListener(this);

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

    }

    @Override
    public void displayShape(Shape shape) {
        playedShape = shape;
        this.shape.setText(shape.toString());
    }

    @Override
    public void onClick(View v) {
        gamePresenter.computeScore(null, playedShape);
    }
}
