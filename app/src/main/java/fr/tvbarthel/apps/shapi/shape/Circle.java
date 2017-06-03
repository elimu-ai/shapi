package fr.tvbarthel.apps.shapi.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * A circle {@link Shape}.
 */
public class Circle implements Shape {

    @Override
    public void draw(Canvas canvas, RectF rect, Paint paint, Paint borderPaint) {
        final float radius = Math.min(rect.height(), rect.width()) / 2f;
        canvas.drawCircle(rect.centerX(), rect.centerY(), radius, paint);
        canvas.drawCircle(rect.centerX(), rect.centerY(), radius, borderPaint);
    }
}
