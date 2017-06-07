package fr.tvbarthel.apps.shapi.shape;

import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * A rectangle {@link Shape}
 */
public class Rectangle extends Shape {

    private static final int SHAPE_COLOR = Colors.rgb(54, 163, 208);
    private static final int BORDER_COLOR = Colors.rgb(29, 116, 166);

    /**
     * Create a rectangle {@link Shape}.
     *
     * @param borderWidth the width, in pixels, of the border.
     */
    public Rectangle(float borderWidth) {
        super(SHAPE_COLOR, BORDER_COLOR, borderWidth);
    }

    @Override
    public void draw(Canvas canvas, RectF rect) {
        if (rect.width() == rect.height()) {
            final float verticalPadding = rect.height() / 2 * 1f / 3f;
            final float top = rect.top + verticalPadding;
            final float bottom = rect.bottom - verticalPadding;

            canvas.drawRect(rect.left, top, rect.right, bottom, shapePaint);
            canvas.drawRect(rect.left, top, rect.right, bottom, borderPaint);
        } else {
            canvas.drawRect(rect.left, rect.top, rect.right, rect.bottom, shapePaint);
            canvas.drawRect(rect.left, rect.top, rect.right, rect.bottom, borderPaint);
        }
    }
}
