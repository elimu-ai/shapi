package fr.tvbarthel.apps.shapi.game;

import fr.tvbarthel.apps.shapi.shape.Shape;

/**
 * Encapsulate data linked to an area where the player can drop a shape.
 */
public class DropZone {

    private final Class<? extends Shape> shape;
    private final String shapeName;

    /**
     * Encapsulate data linked to an area where the player can drop a shape.
     *
     * @param shape shape allowed in this area.
     */
    public DropZone(Class<? extends Shape> shape) {
        this.shape = shape;
        this.shapeName = shape.getSimpleName();
    }

    /**
     * shape allowed in this area.
     *
     * @return shape allowed in this area.
     */
    public Class<? extends Shape> getShape() {
        return shape;
    }

    /**
     * Name of the shape allowed in this area.
     *
     * @return shape allowed in this area.
     */
    public String getShapeName() {
        return shapeName;
    }
}
