package de.huddeldaddel.barcode;

import de.huddeldaddel.barcode.common.Barcode;
import de.huddeldaddel.barcode.interleave2of5.Encoder;
import de.huddeldaddel.barcode.common.ImageGenerator;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Takes some numeric input and a file path. Encodes the numeric input in interleaved 2 of 5 and generates a barcode.
 * 
 * @author Thomas Werner
 */
public class Main {

    private final String numericValue;
    private final String outputPath;
    private final boolean checksum;
    private final int height;
    private final int margin;
    private final boolean showText;
    private final int weight;
    
    protected Main(final CommandLine cli) throws ParseException { 
        numericValue = cli.getParsedOptionValue("value");
        outputPath = cli.getParsedOptionValue("out");
        checksum = cli.hasOption("checksum");
        height = Integer.parseInt(cli.getParsedOptionValue("height", "24"));
        margin = Integer.parseInt(cli.getParsedOptionValue("margin", "5"));
        showText = cli.hasOption("text");
        weight = Integer.parseInt(cli.getParsedOptionValue("weight", "1"));
    }
    
    public static void main(String[] args) throws IOException {
        final Options options = buildCliOptions();        
        final CommandLineParser parser = new DefaultParser();
        try {
            final CommandLine cli = parser.parse(options, args);
            new Main(cli).run();
        } catch (ParseException | NumberFormatException ex) {
            new HelpFormatter().printHelp("barcode", options);
        } catch(IOException iox) {
            System.out.println("IO error: " + iox.getMessage());
        } catch(IllegalArgumentException iae) {
            System.out.println("Processing error: " + iae.getMessage());
        }
    }
    
    protected static Options buildCliOptions() {
        final Options options = new Options();
        options.addOption(Option.builder("value").desc("numeric value of the barcode").hasArg().required().build());
        options.addOption(Option.builder("out").desc("output file").hasArg().required().build());
        options.addOption(Option.builder("height").desc("bar height in px").hasArg().build());
        options.addOption(Option.builder("margin").desc("border width in px").hasArg().build());
        options.addOption(Option.builder("weight").desc("bar weight in px").hasArg().build());
        options.addOption(new Option("text", "print value as text"));
        options.addOption(new Option("checksum", "add checksum"));
        return options;
    }
    
    private void run() throws IOException {
        final Encoder encoder = new Encoder(checksum);
        final Barcode barcode = encoder.encode(getNumericValue());
        final ImageGenerator imageGenerator = new ImageGenerator();
        final String text = isShowText() ? getNumericValue() : null;
        final BufferedImage image = imageGenerator.generate(barcode, height, weight, margin, text);
        final File f = new File(getOutputPath());
        ImageIO.write(image, "PNG", f);
    }

    protected String getNumericValue() {
        return numericValue;
    }

    protected String getOutputPath() {
        return outputPath;
    }

    protected boolean isUsingChecksum() {
        return checksum;
    }
    
    protected int getHeight() {
        return height;
    }

    protected int getMargin() {
        return margin;
    }

    protected boolean isShowText() {
        return showText;
    }

    protected int getWeight() {
        return weight;
    }
    
}
