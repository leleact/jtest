package com.lele.test.base64;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class ConvertTest {

    private static byte[] int2byte(int tmp, int bytes) {
        StringBuilder tmpStr = new StringBuilder(tmp + "");
        int length = tmpStr.length();
        for (int i = 0; i < bytes - length; i++) {
            tmpStr.insert(0, '0');
        }
        return (tmpStr.toString()).getBytes();
    }

    @Test
    public void int2byte() {
        int num = 16;
        int len = 8;

        byte[] arr = ConvertTest.int2byte(num, len);

        for (byte b : arr
             ) {
            System.out.println((int) (b));
        }
    }


    @Test
    public void base64Test() throws UnsupportedEncodingException {
        String str = "abc";
        String base64Str = Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
        System.out.println("base64:[" + base64Str + "]");
        String originstr = new String(Base64.getDecoder().decode(base64Str), "UTF-8");
        System.out.println("originstr:[" + originstr + "]");
    }
}
