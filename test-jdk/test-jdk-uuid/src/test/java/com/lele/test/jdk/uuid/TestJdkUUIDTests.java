package com.lele.test.jdk.uuid;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class TestJdkUUIDTests {

    private static final Logger log = LoggerFactory.getLogger(TestJdkUUIDTests.class);

    @Test
    public void generateUUIDTest() {

        for (int i = 0; i < 10; i++) {

            UUID uuid = UUID.randomUUID();

            long most = uuid.getMostSignificantBits();

            String s = uuid.toString().replace("-", "");

            log.info("s: {}, most : {}", s, most);
        }

    }
}
