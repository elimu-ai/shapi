package fr.tvbarthel.apps.shapi.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * A diamond {@link Shape}.
 */
public class Diamond implements Shape {

    private final Path path = new Path();

    @Override
    public void draw(Canvas canvas, RectF rect, Paint paint) {
        path.reset();
        path.moveTo((rect.left + rect.centerX()) / 2f, rect.centerY());
        path.lineTo(rect.centerX(), rect.top);
        path.lineTo((rect.right + rect.centerX()) / 2f, rect.centerY());
        path.lineTo(rect.centerX(), rect.bottom);
        path.close();

        canvas.drawPath(path, paint);
    }

}
