package de.huddeldaddel.barcode.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Creates graphical representations of barcodes.
 * 
 * @author Thomas Werner
 */
public class ImageGenerator {
    
    public BufferedImage generate(final Barcode barcode, final int height, final int weight, final int margin, 
            final String text) {                
        
        final Rectangle2D textRect = calculateTextDimensions(text);
        final int textHeight = null == text ? 0 : (int)textRect.getHeight() + 2;
        final int imgHeight = height + margin + margin + textHeight;
        final int imgWidth = calculateWidth(barcode, weight) + margin + margin;
        
        final BufferedImage result = new BufferedImage(imgWidth, imgHeight, TYPE_INT_RGB);
        final Graphics2D g2d = result.createGraphics();
        
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, imgWidth, imgHeight);
        
        int x = margin;
        for(Bar bar : barcode.getBars()) {
            g2d.setColor(bar.getColor());
            g2d.fillRect(x, margin, bar.getWidth() * weight, height);
            x += bar.getWidth() * weight;
        }
        if(null != text) {
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
            g2d.drawString(text, (int)((imgWidth -textRect.getWidth()) / 2), imgHeight -2);
        }
        return result;
    }
    
    private int calculateWidth(final Barcode barcode, final int weight) {
        int result = 0;
        for(Bar bar: barcode.getBars()) {
            result += bar.getWidth() * weight;
        }
        return result;
    }
    
    private Rectangle2D calculateTextDimensions(final String text) {
        if(null == text) {
            return new Rectangle(0, 0);
        }
        
        final BufferedImage img = new BufferedImage(100, 100, TYPE_INT_RGB);
        final Graphics2D g2d = img.createGraphics();
        final Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
        return font.getStringBounds(text, g2d.getFontRenderContext());
    }
    
}
