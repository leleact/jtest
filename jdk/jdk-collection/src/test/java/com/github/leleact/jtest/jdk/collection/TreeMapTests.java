package com.github.leleact.jtest.jdk.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

class TreeMapTests {

    @Test
    void firstEntryTest() {
        TreeMap<Integer, String> map = new TreeMap<>();
        Assertions.assertNull(map.firstEntry());
    }
}
