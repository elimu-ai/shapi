package fr.tvbarthel.apps.shapi.shape;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.ColorInt;

import fr.tvbarthel.apps.shapi.game.GameLevels;

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
    Diamond(float borderWidth) {
        super(SHAPE_COLOR, BORDER_COLOR, borderWidth);
    }

    /**
     * Create a diamond {@link Shape}.
     *
     * @param shapeColor  the color of the shape.
     * @param borderColor the color of the border.
     * @param borderWidth the width, in pixels, of the border.
     */
    private Diamond(@ColorInt int shapeColor, @ColorInt int borderColor, float borderWidth) {
        super(shapeColor, borderColor, borderWidth);
    }


    /**
     * Create a {@link Diamond} corresponding to the game level.
     *
     * @param gameLevel   a game level.
     * @param borderWidth the border width.
     * @return a newly created {@link Diamond};
     */
    public static Diamond create(@GameLevels.Level int gameLevel, float borderWidth) {
        if (gameLevel == GameLevels.LEVEL_4) {
            return new Diamond(Shape.DEFAULT_COLOR, Shape.DEFAULT_BORDER_COLOR, borderWidth);
        } else {
            return new Diamond(borderWidth);
        }
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
