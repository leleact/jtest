package com.github.leleact.jest.jdk.bytes;

import com.github.leleact.jtest.jdk.bytes.BytesUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

@Slf4j
public class BytesUtilTests {

    private static final int COUNT = 10000;

    private static final String STR = "接送人家头文件人民公仆为客人普工可破人";

    @Test
    public void byte2hexTest() {
        String res = null;
        long start = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            res = BytesUtil.bytes2hex(STR.getBytes(StandardCharsets.UTF_8));
        }
        log.info("res: {}, escape time: {} ms", res, System.currentTimeMillis() - start);
    }

    @Test
    public void byte2hexV2Test() {
        String res = null;
        long start = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            res = BytesUtil.bytes2hexV2(STR.getBytes(StandardCharsets.UTF_8));
        }
        log.info("res: {}, escape time: {} ms", res, System.currentTimeMillis() - start);

    }

    @Test
    public void byte2hexV3Test() {
        String res = null;
        long start = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            res = BytesUtil.bytes2hexV3(STR.getBytes(StandardCharsets.UTF_8));
        }
        log.info("res: {}, escape time: {} ms", res, System.currentTimeMillis() - start);
    }
}
