package com.lele.test.commons.lang3;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtilTests {

    private static final Logger log = LoggerFactory.getLogger(DateTimeUtilTests.class);

    @Test
    public void dateTImeUtilPerformenceTest() {
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

        Assert.assertTrue(sdfTime > commonlang3Time);

    }
}
