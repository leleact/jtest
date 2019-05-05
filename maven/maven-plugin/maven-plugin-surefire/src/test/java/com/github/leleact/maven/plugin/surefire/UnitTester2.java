package com.github.leleact.maven.plugin.surefire;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class UnitTester2 {

    @Test
    @Category(com.github.leleact.maven.plugin.surefire.Category.OutConnectionTests.class)
    public void test1() {
        System.out.println("xxx");
    }

    @Test
    public void test2() {
        System.out.println("yyy");
    }
}
