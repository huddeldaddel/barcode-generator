package de.huddeldaddel.barcode;

import static de.huddeldaddel.barcode.Main.buildCliOptions;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Options;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 * Tests for the commandline interface.
 * 
 * @author Thomas Werner
 */
public class MainTest {
    
    private static final String CHECKSUM_PARAM = "-checksum";
    private static final String HEIGHT_PARAM = "-height";
    private static final int HEIGHT_VALUE = 25;
    private static final String MARGIN_PARAM = "-margin";
    private static final int MARGIN_VALUE = 3;
    private static final String OUT_PARAM = "-out";
    private static final String OUT_VALUE = "/home/tom/Downloads/12345.png";
    private static final String TEXT_PARAM = "-text";
    private static final String VALUE_PARAM = "-value";
    private static final String VALUE_VALUE = "12345";
    private static final String WEIGHT_PARAM = "-weight";
    private static final int WEIGHT_VALUE = 2;
        
    @Test
    public void testCliMandatoryNoParams() throws Exception {
        final String[] args = { };
        final Options options = buildCliOptions();        
        final CommandLineParser parser = new DefaultParser();
        try {
            parser.parse(options, args);
            fail("Cli config failed to detect missing parameters");
        } catch(MissingOptionException moe) {
            // Cool. This is what we expect
        }
    }
    
    @Test
    public void testCliMandatoryParamsOnly() throws Exception {
        final String[] args = { VALUE_PARAM, VALUE_VALUE, OUT_PARAM, OUT_VALUE };
        final Options options = buildCliOptions();        
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cli = parser.parse(options, args);
        final Main barcode = new Main(cli);
        
        assertEquals(VALUE_VALUE, barcode.getNumericValue());
        assertEquals(OUT_VALUE, barcode.getOutputPath());
        assertEquals(false, barcode.isUsingChecksum());
        assertEquals(false, barcode.isShowText());
    }
    
    @Test
    public void testCliMandatoryAllParams() throws Exception {
        final String[] args = { 
            VALUE_PARAM, VALUE_VALUE, 
            OUT_PARAM, OUT_VALUE, 
            CHECKSUM_PARAM,
            HEIGHT_PARAM, Integer.toString(HEIGHT_VALUE),
            MARGIN_PARAM, Integer.toString(MARGIN_VALUE),
            WEIGHT_PARAM, Integer.toString(WEIGHT_VALUE),
            TEXT_PARAM
        };
        final Options options = buildCliOptions();        
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cli = parser.parse(options, args);
        final Main barcode = new Main(cli);
        
        assertEquals(VALUE_VALUE, barcode.getNumericValue());
        assertEquals(OUT_VALUE, barcode.getOutputPath());
        assertEquals(true, barcode.isUsingChecksum());
        assertEquals(HEIGHT_VALUE, barcode.getHeight());
        assertEquals(MARGIN_VALUE, barcode.getMargin());
        assertEquals(WEIGHT_VALUE, barcode.getWeight());
        assertEquals(true, barcode.isShowText());
    }
    
}
