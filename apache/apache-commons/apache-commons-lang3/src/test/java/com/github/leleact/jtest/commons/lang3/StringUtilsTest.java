package com.github.leleact.jtest.commons.lang3;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;


@Slf4j
class StringUtilsTest {

    @Test
    void splitPreserveAllTokensTest() {
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
    void splitTest() {
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
    void subStringTest() {
        String str = "iodd_dkd_fs.txt";
        log.debug("substringAfter {}", StringUtils.substringAfter(str, "_"));
        log.debug("substringAfterLast {}", StringUtils.substringAfterLast(str, "_"));

        log.debug("substringBefore {}", StringUtils.substringBefore(str, "_"));
        log.debug("substringBeforeLast {}", StringUtils.substringBeforeLast(str, "_"));
    }
}
