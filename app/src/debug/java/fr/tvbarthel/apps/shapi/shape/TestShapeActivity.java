package fr.tvbarthel.apps.shapi.shape;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;

import fr.tvbarthel.apps.shapi.R;

/**
 * A simple {@link AppCompatActivity} used for testing
 * the {@link ShapeView}.
 */
public class TestShapeActivity extends AppCompatActivity {

    private ShapeView shapeView1;
    private ShapeView shapeView2;
    private ShapeView shapeView3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_shape);
        shapeView1 = ((ShapeView) findViewById(R.id.activity_test_shape_view_1));
        shapeView2 = ((ShapeView) findViewById(R.id.activity_test_shape_view_2));
        shapeView3 = ((ShapeView) findViewById(R.id.activity_test_shape_view_3));
    }

    /**
     * Set the {@link Shape} to test.
     *
     * @param shape the {@link Shape} to test.
     */
    @VisibleForTesting
    void setShape(Shape shape) {
        shapeView1.setShape(shape);
        shapeView2.setShape(shape);
        shapeView3.setShape(shape);
    }
}
