package de.huddeldaddel.barcode.interleave2of5;

import de.huddeldaddel.barcode.common.Bar;
import de.huddeldaddel.barcode.common.Barcode;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Encodes input as Interleave 2 of 5 barcode.
 * 
 * @author Jerred Shepherd
 * @author Thomas Werner
 */
public class Encoder {
    
    private static final String ZERO_CODE = "nnwwn";
    private static final String ONE_CODE = "wnnnw";
    private static final String TWO_CODE = "nwnnw";
    private static final String THREE_CODE = "wwnnn";
    private static final String FOUR_CODE = "nnwnw";
    private static final String FIVE_CODE = "wnwnn";
    private static final String SIX_CODE = "nwwnn";
    private static final String SEVEN_CODE = "nnnww";
    private static final String EIGHT_CODE = "wnnwn";
    private static final String NINE_CODE = "nwnwn";
    private static final String START_CODE = "NnNn";
    private static final String END_CODE = "WnN";

    private final Map<String, Bar> bars;
    private final boolean checksums;
    
    public Encoder(final boolean checksums) {
        bars = new HashMap<>();
        bars.put("N", new Bar('N', Color.BLACK, 1));
        bars.put("n", new Bar('n', Color.WHITE, 1));
        bars.put("W", new Bar('W', Color.BLACK, 3));
        bars.put("w", new Bar('w', Color.WHITE, 3));
        
        this.checksums = checksums;
    }
    
    public Barcode encode(final String s) {
        String input = s;
        if(checksums) {
            input = addChecksum(input);
        }
        input = addLeadingZero(input);

        final StringBuilder sb = new StringBuilder();
        sb.append(START_CODE);
        for (int i = 0; i < input.length() - 1; i+= 2) {
            final char[] sChars = input.substring(i, i + 2).toCharArray();
            sb.append(generatePairString(sChars[0], sChars[1]));
        }
        sb.append(END_CODE);
        
        return new Barcode(input, createBars(sb.toString()));
    }

    private List<Bar> createBars(final String input) {
        final List<Bar> result = new ArrayList<>();
        for(int i=0; i<input.length(); i++) {
            result.add(bars.get(Character.toString(input.charAt(i))));
        }
        return result;
    }
        
    private static String addLeadingZero(final String s) {
        return (s.length() % 2 != 0) ? "0" + s : s;
    }
    
    private static String addChecksum(final String input) {
        int sum = 0;
        for(int i=0; i<input.length(); i++) {
            int intValue = Integer.parseInt(Character.toString(input.charAt(i)));
            sum += (i % 2 == 0) ? intValue * 3 : intValue;
        }
        final int M = sum % 10;
        return input + ((M == 0) ? "0" : Integer.toString(10 - M));
    }

    private static String generatePairString(final char l, final char r) {
        final String lCode = getCode(l);
        final String rCode = getCode(r);
        return combineStrings(lCode.toUpperCase(), rCode.toLowerCase());
    }

    private static String combineStrings(final String l, final String r) {
        final char[] lChars = l.toCharArray();
        final char[] rChars = r.toCharArray();
        final char[] combined = new char[10];
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                combined[i] = lChars[i / 2];
            } else {
                combined[i] = rChars[i / 2];
            }
        }
        return String.valueOf(combined);
    }

    private static String getCode(final char c) {
        switch (c) {
            case '0':
                return ZERO_CODE;
            case '1':
                return ONE_CODE;
            case '2':
                return TWO_CODE;
            case '3':
                return THREE_CODE;
            case '4':
                return FOUR_CODE;
            case '5':
                return FIVE_CODE;
            case '6':
                return SIX_CODE;
            case '7':
                return SEVEN_CODE;
            case '8':
                return EIGHT_CODE;
            case '9':
                return NINE_CODE;
            default:
                throw new IllegalArgumentException("Invalid input: " + c);
        }
    }

}
