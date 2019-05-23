package com.github.leleact.jtest.commons.lang3;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
class DateTimeUtilTests {

    @Test
    void dateTimeUtilPerformanceTest() {
        long commonlang3Time = 0;
        long sdfTime = 0;
        int loopTimes = 10000000;
        {
            long start = System.currentTimeMillis();
            for (int i = 0; i < loopTimes; i++) {
                DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
            }
            long end = System.currentTimeMillis();
            commonlang3Time = end - start;
            log.info("time: {}", commonlang3Time);
        }

        {
            long start = System.currentTimeMillis();
            for (int i = 0; i < loopTimes; i++) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                sdf.format(new Date());
            }
            long end = System.currentTimeMillis();
            sdfTime = end - start;
            log.info("time: {}", sdfTime);
        }

        Assertions.assertTrue(sdfTime > commonlang3Time);
    }
}
