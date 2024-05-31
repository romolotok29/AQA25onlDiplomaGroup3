package data;

import java.util.Arrays;

public class BoundaryValues {

    public static final String MAX_PLUS_ONE = generateString(251, '1');

    private static String generateString(int length, char character) {
        char[] chars = new char[length];
        Arrays.fill(chars, character);
        return new String(chars);
    }
}
