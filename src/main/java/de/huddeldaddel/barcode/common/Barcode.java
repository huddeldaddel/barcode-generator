package de.huddeldaddel.barcode.common;

import java.util.List;

/**
 * Represents a value and its barcode representation
 * 
 * @author Thomas Werner
 */
public class Barcode {
    
    private final List<Bar> bars;
    private final String encodedValue;

    public Barcode(final String encodedValue, final List<Bar> bars) {
        this.bars = bars;
        this.encodedValue = encodedValue;
    }
    
    /**
     * @return the bars
     */
    public List<Bar> getBars() {
        return bars;
    }    

    /**
     * @return the encodedValue
     */
    public String getEncodedValue() {
        return encodedValue;
    }
    
    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        for(Bar bar: bars) {
            result.append(bar.toString());
        }
        return result.toString();
    }
    
}
