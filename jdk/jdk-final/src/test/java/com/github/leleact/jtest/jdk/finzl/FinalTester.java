package com.github.leleact.jtest.jdk.finzl;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class FinalTester {


    // 变量sets不可再作为其他对象的引用
    // <code>sets = new HashSet<>()</code>编译错误
    private static final Set<String> sets = new HashSet<>();

    static {
        sets.add("aaa");
        sets.add("bbb");
    }

    @Test
    public void finalTest() {
        Assertions.assertDoesNotThrow(() -> {
            for (String entity : sets) {
                log.info("xxx {}", entity);
            }
        });
    }

}
