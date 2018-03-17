package com.lele.test.commons.lang3;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtilsTest {

    private static Logger log = LoggerFactory.getLogger(StringUtilsTest.class);

    @Test
    public void splitPreserveAllTokensTest() {
        {
            String str = "aaa";
            String arr[] = StringUtils.splitPreserveAllTokens(str, "|");

            for (String s : arr) {
                System.out.println(s);
            }
        }
        System.out.println("==================================");
        {
            String str = "|aaa";
            String arr[] = StringUtils.splitPreserveAllTokens(str, "|");

            for (String s : arr) {
                System.out.println(s);
            }
        }
    }


    @Test
    public void splitTest() {
        {
            String str = "aaa";
            String[] arr = StringUtils.split(str, "|");

            for (String s : arr) {
                System.out.println(s);
            }
        }
        System.out.println("=======================================");
        {
            String str = "|aaa";
            String[] arr = StringUtils.split(str, "|");

            for (String s : arr) {
                System.out.println(s);
            }
        }
    }

    @Test
    public void subStringTest() {
        {
            String str = "iodd_dkd_fs.txt";
            log.debug("substringAfter {}", StringUtils.substringAfter(str, "_"));
            log.debug("substringAfterLast {}", StringUtils.substringAfterLast(str, "_"));

            log.debug("substringBefore {}", StringUtils.substringBefore(str, "_"));
            log.debug("substringBeforeLast {}", StringUtils.substringBeforeLast(str, "_"));
        }
    }
}
