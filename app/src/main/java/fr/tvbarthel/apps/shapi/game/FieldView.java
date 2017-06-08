package fr.tvbarthel.apps.shapi.game;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import fr.tvbarthel.apps.shapi.R;
import fr.tvbarthel.apps.shapi.shape.Shape;
import fr.tvbarthel.apps.shapi.ui.drag.DragHelper;
import fr.tvbarthel.apps.shapi.ui.drag.DragListener;

/**
 * View used to render the game field to the user.
 */
public class FieldView extends FrameLayout {

    private DragHelper dragHelper;
    private DragListener dragListener;
    private Listener listener;

    private DropZoneView dropZone1;
    private DropZoneView dropZone2;
    private DropZoneView dropZone3;
    private DropZoneView dropZone4;
    private OnClickListener internalClickListener;
    private DropZoneView.Listener internalDropZoneListener;
    private FrameLayout dragMask;

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
        //noinspection unchecked
        dragHelper.register(dragMask, dragListener, new ArrayList<Class<?>>(field.getAvailableShapes()));
        displayDropZones(field.getZones());
    }

    /**
     * Listener used to catch view events.
     *
     * @param listener listener used to catch view events.
     */
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /**
     * Initialize internal component.
     */
    private void initialize(Context context) {
        LayoutInflater.from(context).inflate(R.layout.field_view, this);

        initializeInternalListeners();

        dragMask = ((FrameLayout) findViewById(R.id.field_view_drag_mask));
        dropZone1 = ((DropZoneView) findViewById(R.id.field_view_drop_zone_1));
        dropZone2 = ((DropZoneView) findViewById(R.id.field_view_drop_zone_2));
        dropZone3 = ((DropZoneView) findViewById(R.id.field_view_drop_zone_3));
        dropZone4 = ((DropZoneView) findViewById(R.id.field_view_drop_zone_4));

        dragMask.setOnClickListener(internalClickListener);
        dropZone1.setListener(internalDropZoneListener);
        dropZone2.setListener(internalDropZoneListener);
        dropZone3.setListener(internalDropZoneListener);
        dropZone4.setListener(internalDropZoneListener);

        dragHelper = DragHelper.getInstance();
    }

    private void initializeInternalListeners() {
        internalClickListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onEmphasisOnAvailableActionRequested();
                }
            }
        };

        internalDropZoneListener = new DropZoneView.Listener() {

            @Override
            public void onShapeDropped(@NonNull DropZone dropZone, @Nullable Shape shape) {
                if (listener != null) {
                    listener.onShapeDropped(dropZone, shape);
                }
            }
        };
        dragListener = new DragListener() {
            @Override
            protected void onDragDropped(View source, Object data, float x, float y) {
                super.onDragDropped(source, data, x, y);
                animateViewBack(source, x, y);
            }

            @Override
            protected void onDragEnded(View source, Object data) {
                super.onDragEnded(source, data);
                source.setVisibility(VISIBLE);
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

    private void displayDropZones(List<DropZone> zones) {
        if (zones.size() == 1) {
            dropZone1.setVisibility(View.VISIBLE);
            dropZone2.setVisibility(View.GONE);
            dropZone3.setVisibility(View.GONE);
            dropZone4.setVisibility(View.GONE);

            dropZone1.setDropZone(zones.get(0));
        } else if (zones.size() == 2) {
            dropZone1.setVisibility(View.VISIBLE);
            dropZone2.setVisibility(View.VISIBLE);
            dropZone3.setVisibility(View.GONE);
            dropZone4.setVisibility(View.GONE);

            dropZone1.setDropZone(zones.get(0));
            dropZone2.setDropZone(zones.get(1));
        } else if (zones.size() == 4) {
            dropZone1.setVisibility(View.VISIBLE);
            dropZone2.setVisibility(View.VISIBLE);
            dropZone3.setVisibility(View.VISIBLE);
            dropZone4.setVisibility(View.VISIBLE);

            dropZone1.setDropZone(zones.get(0));
            dropZone2.setDropZone(zones.get(1));
            dropZone3.setDropZone(zones.get(2));
            dropZone4.setDropZone(zones.get(3));
        }
    }

    /**
     * Listener used to catch view events.
     */
    public interface Listener {

        /**
         * Called when the user request emphasis on available actions.
         */
        void onEmphasisOnAvailableActionRequested();

        /**
         * Called when the user dropped a shape in the given drop zone.
         *
         * @param dropZone zone in which the user dropped the shape.
         * @param shape    shape dropped by the user.
         */
        void onShapeDropped(@NonNull DropZone dropZone, @Nullable Shape shape);
    }

}
