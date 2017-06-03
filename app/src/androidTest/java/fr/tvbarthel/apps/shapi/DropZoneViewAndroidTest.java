package fr.tvbarthel.apps.shapi;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.squareup.spoon.Spoon;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.tvbarthel.apps.shapi.engine.DropZone;
import fr.tvbarthel.apps.shapi.game.DropZoneView;
import fr.tvbarthel.apps.shapi.shape.Circle;
import fr.tvbarthel.apps.shapi.shape.Diamond;
import fr.tvbarthel.apps.shapi.shape.Rectangle;
import fr.tvbarthel.apps.shapi.shape.TestActivity;
import fr.tvbarthel.apps.shapi.shape.Triangle;

/**
 * Android test for the class {@link DropZoneView}
 */
@RunWith(AndroidJUnit4.class)
public class DropZoneViewAndroidTest {

    @Rule
    public ActivityTestRule<TestActivity> activityRule = new ActivityTestRule<>(TestActivity.class);
    private Instrumentation instrumentation;
    private DropZoneView dropZoneView;

    @Before
    public void setup() {
        instrumentation = InstrumentationRegistry.getInstrumentation();
        setContentView();
        final TestActivity activity = activityRule.getActivity();
        dropZoneView = ((DropZoneView) activity.findViewById(R.id.test_drop_zone_box_view));
    }

    @Test
    public void dropZoneForRectangle() {
        final DropZone dropZone = new DropZone(Rectangle.class);
        final TestActivity activity = activityRule.getActivity();
        setDropZone(dropZone);
        Spoon.screenshot(activity, "drop-zone-for-rectangle");
    }

    @Test
    public void dropZoneForTriangle() {
        final DropZone dropZone = new DropZone(Triangle.class);
        final TestActivity activity = activityRule.getActivity();
        setDropZone(dropZone);
        Spoon.screenshot(activity, "drop-zone-for-triangle");
    }

    @Test
    public void dropZoneForCircle() {
        final DropZone dropZone = new DropZone(Circle.class);
        final TestActivity activity = activityRule.getActivity();
        setDropZone(dropZone);
        Spoon.screenshot(activity, "drop-zone-for-circle");
    }

    @Test
    public void dropZoneForDiamond() {
        final DropZone dropZone = new DropZone(Diamond.class);
        final TestActivity activity = activityRule.getActivity();
        setDropZone(dropZone);
        Spoon.screenshot(activity, "drop-zone-for-diamond");
    }

    private void setContentView() {
        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                activityRule.getActivity().setContentView(R.layout.test_shape_box_view);
            }
        });
    }

    private void setDropZone(final DropZone dropZone) {
        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                dropZoneView.setDropZone(dropZone);
            }
        });
    }
}
