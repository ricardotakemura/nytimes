package android.util;

public class Base64 {

    public static byte[] decode(String str, int flags) {
        return str.getBytes();
    }

    public static byte[] decode(byte[] input, int flags) {
        return input;
    }

    public static byte[] decode(byte[] input, int offset, int len, int flags) {
        return input;
    }

    public static String encodeToString(byte[] input, int flags) {
        return new String(input);
    }

    public static String encodeToString(byte[] input, int offset, int len, int flags) {
        return new String(input);
    }

    public static byte[] encode(byte[] input, int flags) {
        return input;
    }

    public static byte[] encode(byte[] input, int offset, int len, int flags) {
        return input;
    }
}
