package fr.tvbarthel.apps.shapi.game;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import fr.tvbarthel.apps.shapi.R;
import fr.tvbarthel.apps.shapi.shape.Circle;
import fr.tvbarthel.apps.shapi.shape.Diamond;
import fr.tvbarthel.apps.shapi.shape.Rectangle;
import fr.tvbarthel.apps.shapi.shape.Shape;
import fr.tvbarthel.apps.shapi.shape.ShapeView;
import fr.tvbarthel.apps.shapi.shape.Triangle;

/**
 * A {@link android.view.View} representing a {@link DropZone}.
 */
public class DropZoneView extends FrameLayout {

    /**
     * An internal {@link ShapeView}.
     */
    private ShapeView shapeView;
    private Listener listener;

    /**
     * Create a {@link DropZoneView}
     *
     * @param context a {@link Context}
     */
    public DropZoneView(Context context) {
        super(context);
        init(context);
    }

    /**
     * Create a {@link DropZoneView}
     *
     * @param context a {@link Context}
     * @param attrs   an {@link AttributeSet}
     */
    public DropZoneView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * Create a {@link DropZoneView}.
     *
     * @param context      a {@link Context}
     * @param attrs        an {@link AttributeSet}
     * @param defStyleAttr the def style attribute
     */
    public DropZoneView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * Set the {@link DropZone} that should be represented
     *
     * @param dropZone a {@link DropZone}
     */
    public void setDropZone(DropZone dropZone) {
        final Shape shapeForDropZone = getShapeForDropZone(dropZone);
        shapeView.setShape(shapeForDropZone);
    }


    /**
     * Listener used to catch view events.
     *
     * @param listener listener used to catch view events.
     */
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_drop_zone_box, this);
        shapeView = ((ShapeView) findViewById(R.id.view_drop_zone_box_internal_shape));
    }

    private Shape getShapeForDropZone(DropZone dropZone) {
        final Class<? extends Shape> dropZoneShape = dropZone.getShape();
        if (dropZoneShape == Rectangle.class) {
            return new Rectangle();
        } else if (dropZoneShape == Triangle.class) {
            return new Triangle();
        } else if (dropZoneShape == Circle.class) {
            return new Circle();
        } else if (dropZoneShape == Diamond.class) {
            return new Diamond();
        } else {
            throw new IllegalArgumentException("Unsupported drop zone. Found: " + dropZoneShape);
        }
    }

    /**
     * Listener used to catch view events.
     */
    public interface Listener {

        /**
         * Called when the user dropped a shape in the given drop zone.
         *
         * @param dropZone zone in which the user dropped the shape.
         * @param shape    shape dropped by the user.
         */
        void onShapeDropped(@NonNull DropZone dropZone, @Nullable Shape shape);
    }

}
