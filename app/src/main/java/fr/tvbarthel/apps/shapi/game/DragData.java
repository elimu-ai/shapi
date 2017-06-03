package fr.tvbarthel.apps.shapi.game;

import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Encapsulate data related to a drag event.
 */
class DragData {

    /**
     * View fom which drag event has started.
     */
    private WeakReference<View> mSource;

    /**
     * Token used to identify the drag.
     */
    private int mToken;

    /**
     * Additional data passed when drag starts.
     */
    private Object mData;

    /**
     * Default constructor.
     *
     * @param source view from which the drag event started.
     * @param data   additional data send to each event.
     */
    public DragData(View source, Object data) {
        mSource = new WeakReference<>(source);
        mData = data;
    }

    /**
     * Retrieve the view from which the drag event start.
     *
     * @return view.
     */
    public View getSource() {
        View source = mSource.get();
        if (source == null) {
            throw new IllegalStateException("Source view has been destroyed.");
        }
        return mSource.get();
    }

    /**
     * Retrieve the data object passed to the drag event.
     *
     * @return additional data object.
     */
    public Object getData() {
        return mData;
    }

    /**
     * Unique token used to identify the drag event internally.
     *
     * @param token identifier.
     */
    public void setToken(int token) {
        mToken = token;
    }

    /**
     * Retrieve the token used to identify the drag event.
     *
     * @return token used internally.
     */
    public int getToken() {
        return mToken;
    }
}
