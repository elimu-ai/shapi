package fr.tvbarthel.apps.shapi.shape;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.support.annotation.ColorInt;

import fr.tvbarthel.apps.shapi.game.GameLevels;

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
    Circle(float borderWidth) {
        super(SHAPE_COLOR, BORDER_COLOR, borderWidth);
    }

    /**
     * Create a circle {@link Shape}.
     *
     * @param shapeColor  the color of the shape.
     * @param borderColor the color of the border.
     * @param borderWidth the width, in pixels, of the border.
     */
    private Circle(@ColorInt int shapeColor, @ColorInt int borderColor, float borderWidth) {
        super(shapeColor, borderColor, borderWidth);
    }

    /**
     * Create a {@link Circle} corresponding to the game level.
     *
     * @param gameLevel   a game level.
     * @param borderWidth the border width.
     * @return a newly created {@link Circle};
     */
    public static Circle create(@GameLevels.Level int gameLevel, float borderWidth) {
        if (gameLevel == GameLevels.LEVEL_4) {
            return new Circle(Shape.DEFAULT_COLOR, Shape.DEFAULT_BORDER_COLOR, borderWidth);
        } else {
            return new Circle(borderWidth);
        }
    }

    @Override
    public void draw(Canvas canvas, RectF rect) {
        final float radius = Math.min(rect.height(), rect.width()) / 2f;
        canvas.drawCircle(rect.centerX(), rect.centerY(), radius, shapePaint);
        canvas.drawCircle(rect.centerX(), rect.centerY(), radius, borderPaint);
    }
}
