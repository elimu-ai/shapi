package fr.tvbarthel.apps.shapi.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * A shape that kids will learn to identify.
 */
public interface Shape {

    /**
     * Draw this {@link Shape} on a {@link Canvas}.
     *
     * @param canvas      the {@link Canvas} on which the {@link Shape} will be drawn.
     * @param rect        the {@link RectF} representing the drawing area.
     * @param paint       the {@link Paint} to use for drawing.
     * @param borderPaint the {@link Paint} to use for drawing the border of the shapes.
     */
    void draw(Canvas canvas, RectF rect, Paint paint, Paint borderPaint);
}
