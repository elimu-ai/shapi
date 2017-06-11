package fr.tvbarthel.apps.shapi.shape;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.support.annotation.ColorInt;

import fr.tvbarthel.apps.shapi.game.GameLevels;

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
    Rectangle(float borderWidth) {
        super(SHAPE_COLOR, BORDER_COLOR, borderWidth);
    }

    /**
     * Create a rectangle {@link Shape}.
     *
     * @param shapeColor  the color of the shape.
     * @param borderColor the color of the border.
     * @param borderWidth the width, in pixels, of the border.
     */
    private Rectangle(@ColorInt int shapeColor, @ColorInt int borderColor, float borderWidth) {
        super(shapeColor, borderColor, borderWidth);
    }

    /**
     * Create a {@link Rectangle} corresponding to the game level.
     *
     * @param gameLevel   a game level.
     * @param borderWidth the border width.
     * @return a newly created {@link Rectangle};
     */
    public static Rectangle create(@GameLevels.Level int gameLevel, float borderWidth) {
        if (gameLevel == GameLevels.LEVEL_4) {
            return new Rectangle(Shape.DEFAULT_COLOR, Shape.DEFAULT_BORDER_COLOR, borderWidth);
        } else {
            return new Rectangle(borderWidth);
        }
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
