package fr.tvbarthel.apps.shapi.game;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fr.tvbarthel.apps.shapi.shape.Shape;

/**
 * Mocked implementation.
 */
public class DropZonePresenterMockImpl implements DropZoneContract.Presenter {

    private final DragManager dragManager;
    private DropZoneContract.View view;
    private DragListener dragListener;
    private ArrayList<Class<?>> draggableClass;

    /**
     * Mocked implementation.
     *
     * @param dragManager manager used to handle drag motion.
     * @param shapes      list of different shapes type available.
     */
    DropZonePresenterMockImpl(DragManager dragManager, List<Class<? extends Shape>> shapes) {
        this.dragManager = dragManager;

        draggableClass = new ArrayList<>();
        for (Class<? extends Shape> shape : shapes) {
            draggableClass.add(shape);
        }

        dragListener = new DragListener() {
            @Override
            protected void onDragDropped(View source, Object data) {
                super.onDragDropped(source, data);
                if (view != null) {
                    view.displayShapeDropped(((Shape) data));
                }
            }
        };
    }

    @Override
    public void attachView(DropZoneContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView(DropZoneContract.View view) {
        this.view = null;
    }

    @Override
    public void registerDragListener(View view) {
        dragManager.register(view, dragListener, draggableClass);
    }

    @Override
    public void unregisterDragListener(View view) {
        view.setOnDragListener(null);
    }
}
