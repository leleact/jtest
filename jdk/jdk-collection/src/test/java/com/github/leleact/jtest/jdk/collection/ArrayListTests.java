package com.github.leleact.jtest.jdk.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
class ArrayListTests {

    @Test
    void addNullObjectTest() {
        List<String> list = new ArrayList<>();
        list.add(null);
        Assertions.assertEquals(1, list.size());
    }
}
