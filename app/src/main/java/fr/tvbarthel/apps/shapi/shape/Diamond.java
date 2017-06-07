package fr.tvbarthel.apps.shapi.shape;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * A diamond {@link Shape}.
 */
public class Diamond extends Shape {

    private static final int SHAPE_COLOR = Colors.rgb(54, 208, 112);
    private static final int BORDER_COLOR = Colors.rgb(33, 166, 29);

    private final Path path = new Path();

    /**
     * Create a diamond {@link Shape}.
     *
     * @param borderWidth the width, in pixels, of the border.
     */
    public Diamond(float borderWidth) {
        super(SHAPE_COLOR, BORDER_COLOR, borderWidth);
    }

    @Override
    public void draw(Canvas canvas, RectF rect) {
        final float horizontalPadding = rect.width() / 2 * 1f / 4f;
        path.reset();
        path.moveTo(rect.left + horizontalPadding, rect.centerY());
        path.lineTo(rect.centerX(), rect.top);
        path.lineTo(rect.right - horizontalPadding, rect.centerY());
        path.lineTo(rect.centerX(), rect.bottom);
        path.close();

        canvas.drawPath(path, shapePaint);
        canvas.drawPath(path, borderPaint);
    }

}
