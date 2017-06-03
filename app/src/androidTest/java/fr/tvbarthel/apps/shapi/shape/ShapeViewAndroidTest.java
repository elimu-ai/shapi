package fr.tvbarthel.apps.shapi.shape;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.squareup.spoon.Spoon;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.tvbarthel.apps.shapi.R;

/**
 * Android test for the class {@link ShapeView}
 */
@RunWith(AndroidJUnit4.class)
public class ShapeViewAndroidTest {

    @Rule
    public ActivityTestRule<TestActivity> activityRule = new ActivityTestRule<>(TestActivity.class);
    private Instrumentation instrumentation;
    private ShapeView shapeView1;
    private ShapeView shapeView2;
    private ShapeView shapeView3;

    @Before
    public void setup() {
        instrumentation = InstrumentationRegistry.getInstrumentation();
        setContentView();

        final TestActivity activity = activityRule.getActivity();
        shapeView1 = ((ShapeView) activity.findViewById(R.id.activity_test_shape_view_1));
        shapeView2 = ((ShapeView) activity.findViewById(R.id.activity_test_shape_view_2));
        shapeView3 = ((ShapeView) activity.findViewById(R.id.activity_test_shape_view_3));
    }

    @Test
    public void drawRectangleShape() {
        final Rectangle rectangle = new Rectangle();
        final TestActivity activity = activityRule.getActivity();
        setShape(rectangle);
        Spoon.screenshot(activity, "draw-rectangle");
    }

    @Test
    public void drawTriangleShape() {
        final Triangle triangle = new Triangle();
        final TestActivity activity = activityRule.getActivity();
        setShape(triangle);
        Spoon.screenshot(activity, "draw-triangle");
    }

    @Test
    public void drawCircleShape() {
        final Circle circle = new Circle();
        final TestActivity activity = activityRule.getActivity();
        setShape(circle);
        Spoon.screenshot(activity, "draw-circle");
    }

    @Test
    public void drawDiamondShape() {
        final Diamond diamond = new Diamond();
        final TestActivity activity = activityRule.getActivity();
        setShape(diamond);
        Spoon.screenshot(activity, "draw-diamond");
    }

    private void setShape(final Shape shape) {
        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                shapeView1.setShape(shape);
                shapeView2.setShape(shape);
                shapeView3.setShape(shape);
            }
        });
    }

    private void setContentView() {
        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                activityRule.getActivity().setContentView(R.layout.test_shape_view);
            }
        });
    }

}
