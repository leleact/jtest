package com.lele.test.unknow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class JUnitRepeatWorkTests {

    private static final Logger log = LoggerFactory.getLogger(JUnitRepeatWorkTests.class);

    @Repeat(5)
    @Test
    public void repeat() {
        log.info("========how many times the word print!!!====");
    }
}
