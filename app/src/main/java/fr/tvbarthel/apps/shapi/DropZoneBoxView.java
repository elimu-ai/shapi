package fr.tvbarthel.apps.shapi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import fr.tvbarthel.apps.shapi.engine.DropZone;
import fr.tvbarthel.apps.shapi.shape.Circle;
import fr.tvbarthel.apps.shapi.shape.Diamond;
import fr.tvbarthel.apps.shapi.shape.Rectangle;
import fr.tvbarthel.apps.shapi.shape.Shape;
import fr.tvbarthel.apps.shapi.shape.ShapeView;
import fr.tvbarthel.apps.shapi.shape.Triangle;

/**
 * A {@link android.view.View} representing a {@link DropZone}.
 */
public class DropZoneBoxView extends FrameLayout {

    /**
     * An internal {@link ShapeView}.
     */
    private ShapeView shapeView;

    /**
     * Create a {@link DropZoneBoxView}
     *
     * @param context a {@link Context}
     */
    public DropZoneBoxView(Context context) {
        super(context);
        init(context);
    }

    /**
     * Create a {@link DropZoneBoxView}
     *
     * @param context a {@link Context}
     * @param attrs   an {@link AttributeSet}
     */
    public DropZoneBoxView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * Create a {@link DropZoneBoxView}.
     *
     * @param context      a {@link Context}
     * @param attrs        an {@link AttributeSet}
     * @param defStyleAttr the def style attribute
     */
    public DropZoneBoxView(Context context, AttributeSet attrs, int defStyleAttr) {
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


}
