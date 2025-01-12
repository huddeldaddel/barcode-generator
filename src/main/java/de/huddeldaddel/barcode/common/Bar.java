package de.huddeldaddel.barcode.common;

import java.awt.Color;

/**
 * A single bar.
 * 
 * @author Thomas Werner
 */
public class Bar {
    
    private final char code;
    private final Color color;
    private final int width;
    
    public Bar(final char code, final Color color, final int width) {
        this.code = code;
        this.color = color;
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }
    
    @Override
    public String toString() {
        return Character.toString(code);
    }
    
}
