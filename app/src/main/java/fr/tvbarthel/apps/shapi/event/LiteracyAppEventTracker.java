package fr.tvbarthel.apps.shapi.event;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import fr.tvbarthel.apps.shapi.BuildConfig;
import fr.tvbarthel.apps.shapi.shape.Circle;
import fr.tvbarthel.apps.shapi.shape.Rectangle;
import fr.tvbarthel.apps.shapi.shape.Shape;
import fr.tvbarthel.apps.shapi.shape.Triangle;

/**
 * An {@link EventTracker} that uses the literacyapp-org library.
 */
class LiteracyAppEventTracker implements EventTracker {

    private static final String TAG = "LiteracyAppEventTracker";

    private final Context context;

    /**
     * Create a {@link LiteracyAppEventTracker}.
     *
     * @param context a {@link Context}.
     */
    LiteracyAppEventTracker(Context context) {
        this.context = context;
    }

    @Override
    public void trackShapeCorrectlyIdentified(Shape shape) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "trackShapeCorrectlyIdentified: " + shape.getClass());
        } else {
            trackShapeCorrectlyIdentifiedWithLiteracyApp(shape);
        }
    }

    @Nullable
    private org.literacyapp.model.enums.content.Shape mapToLiteracyAppShape(Shape shape) {
        if (shape instanceof Rectangle) {
            return org.literacyapp.model.enums.content.Shape.RECTANGLE_2D;
        } else if (shape instanceof Triangle) {
            return org.literacyapp.model.enums.content.Shape.TRIANGLE_2D;
        } else if (shape instanceof Circle) {
            return org.literacyapp.model.enums.content.Shape.CIRCLE_2D;
        }

        return null;
    }

    private void trackShapeCorrectlyIdentifiedWithLiteracyApp(Shape shape) {
        final org.literacyapp.model.enums.content.Shape literacyAppShape
                = mapToLiteracyAppShape(shape);
        if (literacyAppShape != null) {
            org.literacyapp.analytics.eventtracker.EventTracker
                    .reportShapeLearningEvent(context, literacyAppShape);
        }
    }


}
