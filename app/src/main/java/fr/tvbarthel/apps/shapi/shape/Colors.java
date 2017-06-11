package fr.tvbarthel.apps.shapi.shape;

import android.support.annotation.IntRange;

/**
 * Static methods for dealing with colors.
 */
final class Colors {

    private Colors() {
        //no instance
    }

    /**
     * Return a color-int from red, green, blue components.
     * The alpha component is implicity 255 (fully opaque).
     * These component values should be [0..255], but there is no
     * range check performed, so if they are out of range, the
     * returned color is undefined.
     *
     * @param red   Red component [0..255] of the color
     * @param green Green component [0..255] of the color
     * @param blue  Blue component [0..255] of the color
     * @return the color-int
     */
    @SuppressWarnings("NumericOverflow")
    static int rgb(@IntRange(from = 0, to = 255) int red,
                   @IntRange(from = 0, to = 255) int green,
                   @IntRange(from = 0, to = 255) int blue) {
        return (0xFF << 24) | (red << 16) | (green << 8) | blue;
    }
}
