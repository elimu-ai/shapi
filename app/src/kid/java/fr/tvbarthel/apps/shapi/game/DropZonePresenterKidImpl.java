package fr.tvbarthel.apps.shapi.game;

import java.util.ArrayList;
import java.util.List;

import fr.tvbarthel.apps.shapi.shape.Shape;

/**
 * Implementation for kids.
 */
class DropZonePresenterKidImpl implements DropZoneContract.Presenter {

    private DropZoneContract.View view;
    private ArrayList<Class<?>> draggableClass;

    /**
     * Implementation for kids.
     *
     * @param shapes list of different shapes type available.
     */
    DropZonePresenterKidImpl(List<Class<? extends Shape>> shapes) {
        draggableClass = new ArrayList<>();
        for (Class<? extends Shape> shape : shapes) {
            draggableClass.add(shape);
        }
    }

    @Override
    public void attachView(DropZoneContract.View view) {
        this.view = view;
        this.view.registerDragListener(draggableClass);
    }

    @Override
    public void detachView(DropZoneContract.View view) {
        view.unRegisterDragListener();
        this.view = null;
    }
}
