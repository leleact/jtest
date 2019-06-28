package com.github.leleact.jtest.jdk.base64;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.Base64;

@Slf4j
class ConvertTest {

    private static byte[] int2byte(int tmp, int bytes) {
        StringBuilder tmpStr = new StringBuilder(tmp + "");
        int length = tmpStr.length();
        for (int i = 0; i < bytes - length; i++) {
            tmpStr.insert(0, '0');
        }
        return (tmpStr.toString()).getBytes();
    }

    @Test
    void int2byte() {
        int num = 16;
        int len = 8;

        byte[] arr = ConvertTest.int2byte(num, len);

        for (byte b : arr) {
            System.out.println((int) (b));
        }
    }

    @Test
    void base64Test() {

        String str = "abc +/";
        {
            String res = Base64.getEncoder().encodeToString(str.getBytes(Charset.forName("utf-8")));
            log.debug("{}", res);
        }
        {
            String res = Base64.getUrlEncoder().encodeToString(str.getBytes(Charset.forName("utf-8")));
            log.debug("{}", res);
        }
    }
}
