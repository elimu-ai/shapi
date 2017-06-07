package fr.tvbarthel.apps.shapi.shape;

import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * A circle {@link Shape}.
 */
public class Circle extends Shape {

    private static final int SHAPE_COLOR = Colors.rgb(221, 19, 120);
    private static final int BORDER_COLOR = Colors.rgb(157, 0, 91);

    /**
     * Create a circle {@link Shape}.
     *
     * @param borderWidth the width, in pixels, of the border.
     */
    public Circle(float borderWidth) {
        super(SHAPE_COLOR, BORDER_COLOR, borderWidth);
    }

    @Override
    public void draw(Canvas canvas, RectF rect) {
        final float radius = Math.min(rect.height(), rect.width()) / 2f;
        canvas.drawCircle(rect.centerX(), rect.centerY(), radius, shapePaint);
        canvas.drawCircle(rect.centerX(), rect.centerY(), radius, borderPaint);
    }
}
