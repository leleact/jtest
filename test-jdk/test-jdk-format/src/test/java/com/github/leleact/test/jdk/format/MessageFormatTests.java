package com.github.leleact.test.jdk.format;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class MessageFormatTests {

    private static final Logger logger = LoggerFactory.getLogger(MessageFormatTests.class);

    @Test
    public void formatBaseTest() {

        String s = MessageFormat.format("xx {0} yy {1}", "1", "2");

        logger.info(s);
    }
}
