package com.github.leleact.jtest.jdk.uuid;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@Slf4j
class TestJdkUUIDTests {

    @Test
    void generateUUIDTest() {
        for (int i = 0; i < 10; i++) {
            UUID uuid = UUID.randomUUID();
            long most = uuid.getMostSignificantBits();
            String s = uuid.toString().replace("-", "");
            log.info("s: {}, most : {}", s, most);
        }
    }
}
