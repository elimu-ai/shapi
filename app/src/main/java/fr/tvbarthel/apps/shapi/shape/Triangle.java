package fr.tvbarthel.apps.shapi.shape;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.ColorInt;

import fr.tvbarthel.apps.shapi.game.GameLevels;

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
    Triangle(float borderWidth) {
        super(SHAPE_COLOR, BORDER_COLOR, borderWidth);
    }

    /**
     * Create a triangle {@link Shape}.
     *
     * @param shapeColor  the color of the shape.
     * @param borderColor the color of the border.
     * @param borderWidth the width, in pixels, of the border.
     */
    private Triangle(@ColorInt int shapeColor, @ColorInt int borderColor, float borderWidth) {
        super(shapeColor, borderColor, borderWidth);
    }

    /**
     * Create a {@link Triangle} corresponding to the game level.
     *
     * @param gameLevel   a game level.
     * @param borderWidth the border width.
     * @return a newly created {@link Triangle};
     */
    public static Triangle create(@GameLevels.Level int gameLevel, float borderWidth) {
        if (gameLevel == GameLevels.LEVEL_4) {
            return new Triangle(Shape.DEFAULT_COLOR, Shape.DEFAULT_BORDER_COLOR, borderWidth);
        } else {
            return new Triangle(borderWidth);
        }
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
