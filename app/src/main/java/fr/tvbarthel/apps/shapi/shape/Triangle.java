package fr.tvbarthel.apps.shapi.shape;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * A triangle {@link Shape}.
 */
public class Triangle extends Shape {

    private static final int SHAPE_COLOR = Colors.rgb(247, 147, 30);
    private static final int BORDER_COLOR = Colors.rgb(224, 114, 0);

    private final Path path = new Path();

    /**
     * Create a triangle {@link Shape}.
     *
     * @param borderWidth the width, in pixels, of the border.
     */
    public Triangle(float borderWidth) {
        super(SHAPE_COLOR, BORDER_COLOR, borderWidth);
    }

    @Override
    public void draw(Canvas canvas, RectF rect) {
        path.reset();
        path.moveTo(rect.left, rect.bottom);
        path.lineTo(rect.centerX(), rect.top);
        path.lineTo(rect.right, rect.bottom);
        path.close();

        canvas.drawPath(path, shapePaint);
        canvas.drawPath(path, borderPaint);
    }
}
