package com.github.leleact.jtest.jdk.bytes;

public class BytesUtil {

    private static final char[] BASE16 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final int MASK4 = 0x0f;
    private static final int MASK6 = 0x3f;
    private static final int MASK8 = 0xff;

    public static String bytes2hex(byte[] bs) {
        int len = bs.length;
        int w = 0;
        char[] cs = new char[len * 2];
        for (byte b : bs) {
            cs[w++] = BASE16[b >> 4 & MASK4];
            cs[w++] = BASE16[b & MASK4];
        }
        return new String(cs);
    }

    public static String bytes2hexV2(byte[] bs) {
        StringBuilder buff = new StringBuilder();
        for (byte b : bs) {
            if (Integer.toHexString(0xFF & b).length() == 1) {
                buff.append("0").append(
                    Integer.toHexString(0xFF & b));
            } else {
                buff.append(Integer.toHexString(0xFF & b));
            }
        }
        return buff.toString();
    }

    public static String bytes2hexV3(byte[] bs) {
        StringBuilder buff = new StringBuilder();
        for (byte b : bs) {
            buff.append(String.format("%02X", b));
        }
        return buff.toString();
    }

    public static byte[] hex2bytes(final String str) {
        int len = str.length();
        int num = len / 2;
        int r = 0;
        int w = 0;
        byte[] b = new byte[num];
        for (int i = 0; i < num; i++) {
            b[w++] = (byte) (hex(str.charAt(r++)) << 4 | hex(str.charAt(r++)) & 0xff);
        }
        return b;
    }

    private static byte hex(char c) {
        if (c <= '9') return (byte) (c - '0');
        if (c >= 'a' && c <= 'f') return (byte) (c - 'a' + 10);
        if (c >= 'A' && c <= 'F') return (byte) (c - 'A' + 10);
        throw new IllegalArgumentException("hex string format error [" + c + "].");
    }
}
