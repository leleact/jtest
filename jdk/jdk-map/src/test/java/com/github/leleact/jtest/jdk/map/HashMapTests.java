package com.github.leleact.jtest.jdk.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTests {

    @Test
    public void nullKeyTest() {
        Map<String, String> map = new HashMap<>();
        String value = map.get(null);
        Assertions.assertNull(value);
    }

    @Test
    public void getDefaultTest() {
        Map<String, String> m = new HashMap<>();
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            m.computeIfAbsent("key", k -> {
                throw new UnsupportedOperationException("Not supported yet.");
            });
        });
    }
}
