package fr.tvbarthel.apps.shapi.ui.drag;

import android.content.ClipData;
import android.os.Build;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manager drag motion.
 */
public final class DragHelper {

    private static DragHelper sInstance;

    private HashMap<Integer, DragData> mDraggedItems;

    private DragHelper() {
        mDraggedItems = new HashMap<>();
    }

    /**
     * Drag motion helper instance.
     *
     * @return drag manager.
     */
    public static DragHelper getInstance() {
        if (sInstance == null) {
            sInstance = new DragHelper();
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
    public void startDrag(View source, View.DragShadowBuilder shadow, Object data) {
        ClipData clipData = ClipData.newPlainText(data.getClass().getName(), "");
        DragData dragData = new DragData(source, data);
        int token = clipData.getDescription().hashCode();
        mDraggedItems.put(token, dragData);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            DragStarterPostNougatImpl.startDrag(source, clipData, shadow, token, 0);
        } else {
            DragStarterPreNougatImpl.startDrag(source, clipData, shadow, token, 0);
        }
    }

    /**
     * Register a drag listener.
     *
     * @param area           drop area
     * @param listener       listener
     * @param droppableClass class which can be droppable.
     */
    public void register(View area, DragListener listener, ArrayList<Class<?>> droppableClass) {
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
            sInstance = new DragHelper();
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
            sInstance = new DragHelper();
        }
        sInstance.mDraggedItems.remove(token);
    }

}
