package fr.tvbarthel.apps.shapi.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * A shape that kids will learn to identify.
 */
public abstract class Shape {

    /**
     * A default color for the {@link Shape}
     */
    public static final int DEFAULT_COLOR = Colors.rgb(0xb1, 0x7a, 0xba);

    /**
     * A default color for the border of a {@link Shape}.
     */
    public static final int DEFAULT_BORDER_COLOR = Colors.rgb(0x7c, 0x3e, 0x93);

    /**
     * The {@link Paint} that should be used to draw the shape.
     */
    protected final Paint shapePaint;

    /**
     * The {@link Paint} that should be used to draw the border of the shape.
     */
    protected final Paint borderPaint;

    /**
     * A shape that kids will learn to identify.
     *
     * @param shapeColor  the color of the shape.
     * @param borderColor the color of the border of the shape.
     * @param borderWidth the width, in pixels, of the border of the shape.
     */
    protected Shape(int shapeColor, int borderColor, float borderWidth) {
        shapePaint = new Paint();
        shapePaint.setColor(shapeColor);
        shapePaint.setAntiAlias(true);
        shapePaint.setStyle(Paint.Style.FILL);

        borderPaint = new Paint();
        borderPaint.setColor(borderColor);
        borderPaint.setAntiAlias(true);
        borderPaint.setStrokeWidth(borderWidth);
        borderPaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * Draw this {@link Shape} on a {@link Canvas}.
     *
     * @param canvas the {@link Canvas} on which the {@link Shape} will be drawn.
     * @param rect   the {@link RectF} representing the drawing area.
     */
    abstract void draw(Canvas canvas, RectF rect);
}
