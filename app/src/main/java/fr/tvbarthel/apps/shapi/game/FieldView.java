package fr.tvbarthel.apps.shapi.game;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import fr.tvbarthel.apps.shapi.ui.drag.DragHelper;
import fr.tvbarthel.apps.shapi.ui.drag.DragListener;

/**
 * View used to render the game field to the user.
 */
public class FieldView extends FrameLayout {

    private DragHelper dragHelper;
    private Field field;
    private DragListener dragListener;

    /**
     * View used to render the game field to the user.
     *
     * @param context a {@link Context}
     */
    public FieldView(Context context) {
        super(context);
        initialize(context);
    }

    /**
     * View used to render the game field to the user.
     *
     * @param context a {@link Context}
     * @param attrs   an {@link AttributeSet}
     */
    public FieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    /**
     * View used to render the game field to the user..
     *
     * @param context      a {@link Context}
     * @param attrs        an {@link AttributeSet}
     * @param defStyleAttr the def style attribute
     */
    public FieldView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    /**
     * Set the field to display to the user.
     *
     * @param field field to display.
     */
    public void setField(@NonNull Field field) {
        this.field = field;
        dragHelper.register(this, dragListener, field.getAvailableShapes());
    }


    /**
     * Initialize internal component.
     */
    private void initialize(Context context) {
        dragHelper = DragHelper.getInstance();
        dragListener = new DragListener() {
            @Override
            protected void onDragDropped(View source, Object data, float x, float y) {
                super.onDragDropped(source, data, x, y);
                animateViewBack(source, x, y);
            }
        };
    }

    private void animateViewBack(View source, float dropX, float dropY) {
        source.setTranslationX(dropX - (source.getX() + source.getWidth() / 2));
        source.setTranslationY(dropY - (source.getY() + source.getHeight() / 2));
        source.setAlpha(0.5f);
        source.setVisibility(VISIBLE);
        source.animate().alpha(1f).translationX(0).translationY(0).setDuration(300).setListener(null);
    }

}
