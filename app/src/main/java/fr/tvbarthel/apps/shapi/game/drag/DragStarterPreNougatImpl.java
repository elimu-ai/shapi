package fr.tvbarthel.apps.shapi.game.drag;

import android.content.ClipData;
import android.view.View;

/**
 * Used to start a drag on a {@link View} on pre Nougat devices.
 */
@SuppressWarnings("deprecation")
final class DragStarterPreNougatImpl {

    /**
     * Non instantiable class.
     */
    private DragStarterPreNougatImpl() {

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
        source.startDrag(clipData, shadow, token, i);
    }
}
