package com.github.leleact.jtest.jdk.collection;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTests {

    private static final Logger log = LoggerFactory.getLogger(ArrayListTests.class);

    @Test
    public void addNullObjectTest() {

        List<String> list = new ArrayList<>();

        list.add(null);

        Assert.assertEquals(1, list.size());
    }
}
