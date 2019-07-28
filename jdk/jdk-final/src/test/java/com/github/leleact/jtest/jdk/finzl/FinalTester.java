package com.github.leleact.jtest.jdk.finzl;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class FinalTester {

    private static final Logger log = LoggerFactory.getLogger(FinalTester.class);

    // 变量sets不可再作为其他对象的引用
    // <code>sets = new HashSet<>()</code>编译错误
    private static final Set<String> sets = new HashSet<>();

    static {
        sets.add("aaa");
        sets.add("bbb");
    }

    @Test
    public void finalTest() {
        for (String entity : sets) {
            log.info("xxx {}", entity);
        }
    }

}
