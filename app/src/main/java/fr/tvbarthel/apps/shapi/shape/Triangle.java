package fr.tvbarthel.apps.shapi.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * A triangle {@link Shape}.
 */
public class Triangle implements Shape {

    private final Path path = new Path();

    @Override
    public void draw(Canvas canvas, RectF rect, Paint paint, Paint borderPaint) {
        path.reset();
        path.moveTo(rect.left, rect.bottom);
        path.lineTo(rect.centerX(), rect.top);
        path.lineTo(rect.right, rect.bottom);
        path.close();

        canvas.drawPath(path, paint);
        canvas.drawPath(path, borderPaint);
    }
}
