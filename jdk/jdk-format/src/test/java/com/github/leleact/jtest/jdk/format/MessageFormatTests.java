package com.github.leleact.jtest.jdk.format;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
class MessageFormatTests {

    @Test
    void formatBaseTest() {

        String s = MessageFormat.format("xx {0} yy {1}", "1", "2");

        log.info(s);
    }

    @Test
    void formatArrayTest() {
        List<Object> objectList = new ArrayList<>();
        objectList.add("1");
        objectList.add(2);
        String s = MessageFormat.format("XX {0} yy {1}", objectList.toArray());
        log.info(s);
    }

    @Test
    void nullFormatTest() {
        Assertions.assertEquals("X{0}", MessageFormat.format("X{0}", null));
        Assertions.assertEquals("X12", MessageFormat.format("X{0}{1}", 1, 2));
    }

    @Test
    void noPlaceHolderTest() {
        Assertions.assertEquals("X", MessageFormat.format("X", 1, 2));
    }
}
