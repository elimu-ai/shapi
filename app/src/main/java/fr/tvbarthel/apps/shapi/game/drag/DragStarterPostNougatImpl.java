package fr.tvbarthel.apps.shapi.game.drag;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.os.Build;
import android.view.View;

/**
 * Used to start a drag on a {@link View} on post Nougat devices.
 */
@TargetApi(Build.VERSION_CODES.N)
final class DragStarterPostNougatImpl {

    /**
     * Non instantiable class.
     */
    private DragStarterPostNougatImpl() {

    }

    /**
     * Used to start a drag on the given view.
     *
     * @param source   view from which the drag motion is started.
     * @param clipData data linked to the drag motion.
     * @param shadow   shadow displayed during the drag.
     * @param token    token.
     * @param i        flags.
     */
    public static void startDrag(View source, ClipData clipData, View.DragShadowBuilder shadow, int token, int i) {
        source.startDragAndDrop(clipData, shadow, token, i);
    }
}
