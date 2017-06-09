package fr.tvbarthel.apps.shapi.shape;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import fr.tvbarthel.apps.shapi.R;
import fr.tvbarthel.apps.shapi.ui.drag.DragHelper;

/**
 * A {@link View} responsible of drawing a {@link Shape}
 */
public class ShapeView extends View {

    private RectF shapeRect;
    private Shape shape;
    private DragHelper dragHelper;
    private Animation wiggleAnimation;

    /**
     * A {@link View} responsible of drawing a {@link Shape}
     *
     * @param context a {@link Context}
     */
    public ShapeView(Context context) {
        super(context);
        init(context);
    }

    /**
     * A {@link View} responsible of drawing a {@link Shape}
     *
     * @param context a {@link Context}
     * @param attrs   an {@link AttributeSet}
     */
    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
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
        init(context);
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
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (shape != null) {
            shape.draw(canvas, shapeRect);
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

    /**
     * Set the {@link Shape} to draw.
     *
     * @param shape a {@link Shape}.
     */
    public void setShape(@Nullable Shape shape) {
        this.shape = shape;
        invalidate();
    }

    /**
     * {@link Shape} currently displayed inside the given view.
     *
     * @return shape currently displayed.
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * Animate the {@link ShapeView} so that user understant available actions.
     */
    public void animateActionIndicator() {
        setVisibility(VISIBLE);
        startAnimation(wiggleAnimation);
    }

    private void init(Context context) {
        initTouchListener();
        dragHelper = DragHelper.getInstance();
        shapeRect = new RectF();
        wiggleAnimation = AnimationUtils.loadAnimation(context, R.anim.wiggle);
    }

    private void initTouchListener() {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    startDragMotion();
                }
                return false;
            }
        });
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
    }

    private void startDragMotion() {
        ShapeView.this.setVisibility(INVISIBLE);
        dragHelper.startDrag(ShapeView.this, new DragShadowBuilder(ShapeView.this), shape);
    }
}
