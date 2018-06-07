package com.lele.test.jdk.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.TreeMap;

public class TreeMapTests {

    @Test
    public void firstEntryTest() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(2, "2");
        map.put(1, "1");
        map.put(3, "3");
        Assert.assertEquals("1", map.firstEntry().getValue());

        map.put(-1, "-1");
        Assert.assertEquals("-1", map.firstEntry().getValue());
    }
}
