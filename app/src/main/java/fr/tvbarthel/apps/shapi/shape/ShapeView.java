package fr.tvbarthel.apps.shapi.shape;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * A {@link View} responsible of drawing a {@link Shape}
 */
public class ShapeView extends View {

    private RectF shapeRect;
    private Paint paint;
    private Shape shape;

    /**
     * A {@link View} responsible of drawing a {@link Shape}
     *
     * @param context a {@link Context}
     */
    public ShapeView(Context context) {
        super(context);
        init();
    }

    /**
     * A {@link View} responsible of drawing a {@link Shape}
     *
     * @param context a {@link Context}
     * @param attrs   an {@link AttributeSet}
     */
    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * A {@link View} responsible of drawing a {@link Shape}
     *
     * @param context      a {@link Context}
     * @param attrs        an {@link AttributeSet}
     * @param defStyleAttr def style attribute
     */
    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * A {@link View} responsible of drawing a {@link Shape}
     *
     * @param context      a {@link Context}
     * @param attrs        an {@link AttributeSet}
     * @param defStyleAttr def style attribute
     * @param defStyleRes  def style resource
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    /**
     * Set the {@link Shape} to draw.
     *
     * @param shape a {@link Shape}.
     */
    public void setShape(@Nullable Shape shape) {
        this.shape = shape;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (shape != null) {
            shape.draw(canvas, shapeRect, paint);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        final int shapeLeft = getPaddingLeft();
        final int shapeRight = getMeasuredWidth() - getPaddingRight();
        final int shapeTop = getPaddingTop();
        final int shareBottom = getMeasuredHeight() - getPaddingBottom();

        shapeRect.set(shapeLeft, shapeTop, shapeRight, shareBottom);
    }

    private void init() {
        shapeRect = new RectF();

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }
}
