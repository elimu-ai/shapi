package fr.tvbarthel.apps.shapi.ui.drag;

import android.content.ClipDescription;
import android.view.DragEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Simple drag listener.
 * Don't support multi-drag.
 */
public class DragListener {

    /**
     * {@link View.OnDragListener} used internally.
     */
    private View.OnDragListener mInternalDragListener;

    /**
     * Object class name which are handle by drag motion.
     */
    private ArrayList<String> mHandledClassNames;

    /**
     * Current dragged item.
     */
    private DragData mDraggedItem;

    /**
     * Default constructor.
     */
    public DragListener() {
        mHandledClassNames = new ArrayList<>();
        initiliazeInternalListener();
    }

    /**
     * Add a classname that will allow a drag event to be handle based on the class object passed
     * as data.
     *
     * @param name class name.
     */
    void addClassName(String name) {
        mHandledClassNames.add(name);
    }

    /**
     * Retrieve the internal drag listener.
     *
     * @return internal drag listener.
     */
    View.OnDragListener getInternalDragListener() {
        return mInternalDragListener;
    }

    /**
     * Reset internal state.
     */
    void reset() {
        mHandledClassNames.clear();
        mDraggedItem = null;
    }

    /**
     * Designed for inheritance.
     *
     * @param source dragged item view source.
     * @param data   data passed when drag was started.
     */
    protected void onDragStarted(View source, Object data) {

    }

    /**
     * Designed for inheritance.
     *
     * @param source dragged item view source.
     * @param data   data passed when drag was started.
     */
    protected void onDragEntered(View source, Object data) {

    }

    /**
     * Designed for inheritance.
     *
     * @param source dragged item view source.
     * @param data   data passed when drag was started.
     * @param x      x position of the dragging point.
     * @param y      y position of the dragging point.
     */
    protected void onDragLocation(View source, Object data, float x, float y) {

    }

    /**
     * Designed for inheritance.
     *
     * @param source dragged item view source.
     * @param data   data passed when drag was started.
     * @param x      x position of the dragging point.
     * @param y      y position of the dragging point.
     */
    protected void onDragDropped(View source, Object data, float x, float y) {

    }

    /**
     * Designed for inheritance.
     *
     * @param source dragged item view source.
     * @param data   data passed when drag was started.
     */
    protected void onDragExited(View source, Object data) {

    }

    /**
     * Designed for inheritance.
     *
     * @param source dragged item view source.
     * @param data   data passed when drag was started.
     */
    protected void onDragEnded(View source, Object data) {

    }

    private void initiliazeInternalListener() {
        mInternalDragListener = new View.OnDragListener() {

            @Override
            public boolean onDrag(View v, DragEvent event) {

                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:

                        // check if listener could catch this event.
                        ClipDescription description = event.getClipDescription();
                        if (description == null) {
                            return false;
                        }
                        String className = String.valueOf(description.getLabel());
                        if (mDraggedItem == null && mHandledClassNames.contains(className)) {

                            // retrieve the data object linked to the dragged event.
                            mDraggedItem = DragHelper.getDraggedItem(((Integer) event.getLocalState()));
                            onDragStarted(mDraggedItem.getSource(), mDraggedItem.getData());

                            return true;
                        } else {
                            return false;
                        }
                    case DragEvent.ACTION_DRAG_ENTERED:
                        onDragEntered(mDraggedItem.getSource(), mDraggedItem.getData());
                        break;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        onDragLocation(
                                mDraggedItem.getSource(), mDraggedItem.getData(),
                                event.getX(), event.getY()
                        );
                        break;
                    case DragEvent.ACTION_DROP:
                        onDragDropped(
                                mDraggedItem.getSource(), mDraggedItem.getData(),
                                event.getX(), event.getY()
                        );
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        onDragExited(mDraggedItem.getSource(), mDraggedItem.getData());
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if (mDraggedItem != null) {
                            onDragEnded(mDraggedItem.getSource(), mDraggedItem.getData());
                            DragHelper.clearDraggedItem(mDraggedItem.getToken());
                            mDraggedItem = null;
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        };
    }
}
