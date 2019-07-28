package com.github.leleact.jtest.jdk.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.TreeMap;

public class TreeMapTests {

    @Test
    public void firstEntryTest() {

        TreeMap<Integer, String> map = new TreeMap<>();

        Assert.assertNull(map.firstEntry());

    }
}
