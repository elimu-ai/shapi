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

/**
 * Android test for the class {@link ShapeView}
 */
@RunWith(AndroidJUnit4.class)
public class ShapeViewAndroidTest {

    @Rule
    public ActivityTestRule<TestShapeActivity> activityRule = new ActivityTestRule<>(TestShapeActivity.class);
    private Instrumentation instrumentation;

    @Before
    public void setup() {
        instrumentation = InstrumentationRegistry.getInstrumentation();
    }


    @Test
    public void drawRectangleShape() {
        final Rectangle rectangle = new Rectangle();
        final TestShapeActivity activity = activityRule.getActivity();
        setShape(activity, rectangle);
        Spoon.screenshot(activity, "draw-rectangle");
    }

    @Test
    public void drawTriangleShape() {
        final Triangle triangle = new Triangle();
        final TestShapeActivity activity = activityRule.getActivity();
        setShape(activity, triangle);
        Spoon.screenshot(activity, "draw-triangle");
    }

    @Test
    public void drawCircleShape() {
        final Circle circle = new Circle();
        final TestShapeActivity activity = activityRule.getActivity();
        setShape(activity, circle);
        Spoon.screenshot(activity, "draw-circle");
    }

    @Test
    public void drawDiamondShape() {
        final Diamond diamond = new Diamond();
        final TestShapeActivity activity = activityRule.getActivity();
        setShape(activity, diamond);
        Spoon.screenshot(activity, "draw-diamond");
    }

    private void setShape(final TestShapeActivity shapeActivity, final Shape shape) {
        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                shapeActivity.setShape(shape);
            }
        });
    }


}
