package fr.tvbarthel.apps.shapi.game;

import android.content.ClipData;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manager drag motion.
 */
final class DragManager {

    private static DragManager sInstance;

    private HashMap<Integer, DragData> mDraggedItems;

    private DragManager() {
        mDraggedItems = new HashMap<>();
    }

    /**
     * Drag motion manager instance.
     *
     * @return drag manager.
     */
    static DragManager getInstance() {
        if (sInstance == null) {
            sInstance = new DragManager();
        }
        return sInstance;
    }

    /**
     * Start a drag motion.
     *
     * @param source source
     * @param shadow shadow
     * @param data   data
     */
    void startDrag(View source, View.DragShadowBuilder shadow, Object data) {
        ClipData clipData = ClipData.newPlainText(data.getClass().getName(), "");
        DragData dragData = new DragData(source, data);
        int token = clipData.getDescription().hashCode();
        mDraggedItems.put(token, dragData);
        source.startDrag(clipData, shadow, token, 0);
    }

    /**
     * Register a drag listener.
     *
     * @param area           drop area
     * @param listener       listener
     * @param droppableClass class which can be droppable.
     */
    void register(View area, DragListener listener, ArrayList<Class<?>> droppableClass) {
        for (Class<?> droppable : droppableClass) {
            listener.addClassName(droppable.getName());
        }
        area.setOnDragListener(listener.getInternalDragListener());
    }

    /**
     * Used internally to retrieve data linked to a drag event.
     *
     * @param token token passed as local state.
     * @return drag data.
     */
    static DragData getDraggedItem(int token) {
        if (sInstance == null) {
            sInstance = new DragManager();
        }
        return sInstance.mDraggedItems.get(token);
    }

    /**
     * Remove the stored data for a drag event.
     *
     * @param token token passed as local state.
     */
    static void clearDraggedItem(int token) {
        if (sInstance == null) {
            sInstance = new DragManager();
        }
        sInstance.mDraggedItems.remove(token);
    }

}
