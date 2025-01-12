package de.huddeldaddel.barcode.interleave2of5;

import de.huddeldaddel.barcode.common.Barcode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Encoder class
 * 
 * @author Thomas Werner
 */
public class EncoderTest {
    
    @Test
    public void testEncodeWithoutChecksum1() {
        final Encoder encoder = new Encoder(false);
        final Barcode barcode = encoder.encode("9");
        
        assertEquals("NnNnNnNwWnWwNnWnN", barcode.toString());
        assertEquals("09", barcode.getEncodedValue());
    }
    
    @Test
    public void testEncodeWithoutChecksum2() {
        final Encoder encoder = new Encoder(false);
        final Barcode barcode = encoder.encode("01");
       
        assertEquals("NnNnNwNnWnWnNwWnN", barcode.toString());
        assertEquals("01", barcode.getEncodedValue());
    }
    
    @Test
    public void testEncodeWithoutChecksum3() {
        final Encoder encoder = new Encoder(false);
        final Barcode barcode = encoder.encode("123");
       
        assertEquals("NnNnNwNnWnWnNwNwWwNnNnWnWnN", barcode.toString());
        assertEquals("0123", barcode.getEncodedValue());
    }
    
    @Test
    public void testEncodeWithoutChecksum4() {
        final Encoder encoder = new Encoder(false);
        final Barcode barcode = encoder.encode("1234");
       
        assertEquals("NnNnWnNwNnNnWwWnWnNwNnNwWnN", barcode.toString());
        assertEquals("1234", barcode.getEncodedValue());
    }
    
    @Test
    public void testEncodeWithoutChecksum5() {
        final Encoder encoder = new Encoder(false);
        final Barcode barcode = encoder.encode("54321");
       
        assertEquals("NnNnNwNnWwWnNnNwNwWnNnWnNwWnNnNnWwWnN", barcode.toString());
        assertEquals("054321", barcode.getEncodedValue());
    }
    
    @Test
    public void testEncodeWithoutChecksum6() {
        final Encoder encoder = new Encoder(false);
        final Barcode barcode = encoder.encode("95551212");
       
        assertEquals("NnNnNwWnNwWnNnWwNnWwNnNnWnNwNnNnWwWnNwNnNnWwWnN", barcode.toString());
        assertEquals("95551212", barcode.getEncodedValue());
    }
    
    @Test
    public void testEncodeWithoutChecksum7() {
        final Encoder encoder = new Encoder(false);
        final Barcode barcode = encoder.encode("6");
       
        assertEquals("NnNnNnNwWwWnNnWnN", barcode.toString());
        assertEquals("06", barcode.getEncodedValue());
    }
    
    @Test
    public void testEncodeWithoutChecksum8() {
        final Encoder encoder = new Encoder(false);
        final Barcode barcode = encoder.encode("66");
       
        assertEquals("NnNnNnWwWwNnNnWnN", barcode.toString());
        assertEquals("66", barcode.getEncodedValue());
    }
    
    @Test
    public void testEncodeWithoutChecksum9() {
        final Encoder encoder = new Encoder(false);
        final Barcode barcode = encoder.encode("666");
       
        assertEquals("NnNnNnNwWwWnNnNnWwWwNnNnWnN", barcode.toString());
        assertEquals("0666", barcode.getEncodedValue());
    }
    
    @Test
    public void testEncodeWithoutChecksum10() {
        final Encoder encoder = new Encoder(false);
        final Barcode barcode = encoder.encode("78");
       
        assertEquals("NnNnNwNnNnWwWnWnN", barcode.toString());
        assertEquals("78", barcode.getEncodedValue());
    }
    
    @Test
    public void testEncodeWithoutChecksum11() {
        final Encoder encoder = new Encoder(false);
        final Barcode barcode = encoder.encode("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
       
        assertEquals("NnNnWnNwNnNnWwWnWnNwNnNwWnNwWwNnNnNwNnNnWwWnNnWnNwWwNnWnNwNnNnWwWnWnNwNnNwWnNwWwNnNnNwNnNnWwWnNnWnNwWwNnWnNwNnNnWwWnWnNwNnNwWnNwWwNnNnNwNnNnWwWnNnWnNwWwNnWnNwNnNnWwWnWnNwNnNwWnNwWwNnNnNwNnNnWwWnNnWnNwWwNnWnNwNnNnWwWnWnNwNnNwWnNwWwNnNnNwNnNnWwWnNnWnNwWwNnWnNwNnNnWwWnWnNwNnNwWnNwWwNnNnNwNnNnWwWnNnWnNwWwNnWnNwNnNnWwWnWnNwNnNwWnNwWwNnNnNwNnNnWwWnNnWnNwWwNnWnNwNnNnWwWnWnNwNnNwWnNwWwNnNnNwNnNnWwWnNnWnNwWwNnWnNwNnNnWwWnWnNwNnNwWnNwWwNnNnNwNnNnWwWnNnWnNwWwNnWnNwNnNnWwWnWnNwNnNwWnNwWwNnNnNwNnNnWwWnNnWnNwWwNnWnN", barcode.toString());
        assertEquals("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890", barcode.getEncodedValue());
    }
    
    @Test
    public void testEncodeWithoutChecksum12() {
        final Encoder encoder = new Encoder(false);
        final Barcode barcode = encoder.encode("0");
       
        assertEquals("NnNnNnNnWwWwNnWnN", barcode.toString());
        assertEquals("00", barcode.getEncodedValue());
    }
    
    @Test
    public void testEncodeWithChecksum1() {
        final Encoder encoder = new Encoder(true);
        final String result = encoder.encode("9").getEncodedValue();
        
        assertEquals("93", result);
    }
    
    @Test
    public void testEncodeWithChecksum2() {
        final Encoder encoder = new Encoder(true);
        final String result = encoder.encode("01").getEncodedValue();
        
        assertEquals("0019", result);
    }
    
    @Test
    public void testEncodeWithChecksum3() {
        final Encoder encoder = new Encoder(true);
        final String result = encoder.encode("123").getEncodedValue();
        
        assertEquals( "1236", result);
    }
    
    @Test
    public void testEncodeWithChecksum4() {
        final Encoder encoder = new Encoder(true);
        final String result = encoder.encode("1234").getEncodedValue();
        
        assertEquals("012342", result);
    }
    
    @Test
    public void testEncodeWithChecksum5() {
        final Encoder encoder = new Encoder(true);
        final String result = encoder.encode("54321").getEncodedValue();
        
        assertEquals("543217", result);
    }
    
    @Test
    public void testEncodeWithChecksum6() {
        final Encoder encoder = new Encoder(true);
        final String result = encoder.encode("95551212").getEncodedValue();
        
        assertEquals("0955512128", result);
    }
    
    @Test
    public void testEncodeWithohecksum7() {
        final Encoder encoder = new Encoder(true);
        final String result = encoder.encode("6").getEncodedValue();
        
        assertEquals("62", result);
    }
    
    @Test
    public void testEncodeWithChecksum8() {
        final Encoder encoder = new Encoder(true);
        final String result = encoder.encode("66").getEncodedValue();
        
        assertEquals("0666", result);
    }
    
    @Test
    public void testEncodeWithChecksum9() {
        final Encoder encoder = new Encoder(true);
        final String result = encoder.encode("666").getEncodedValue();
        
        assertEquals("6668", result);
    }
    
    @Test
    public void testEncodeWithChecksum10() {
        final Encoder encoder = new Encoder(true);
        final String result = encoder.encode("78").getEncodedValue();
        
        assertEquals("0781", result);
    }
    
    @Test
    public void testEncodeWithChecksum11() {
        final String input = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
        final String expResult = "012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678900";
        
        final Encoder encoder = new Encoder(true);
        final String result = encoder.encode(input).getEncodedValue();
        System.out.println(result);
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEncodeWithChecksum12() {
        final Encoder encoder = new Encoder(true);
        final String result = encoder.encode("0").getEncodedValue();
        
        assertEquals("00", result);
    }
    
}
