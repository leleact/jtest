package com.github.leleact.maven.plugin.surefire;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class UnitTester1 {

    @Test
    @Category(com.github.leleact.maven.plugin.surefire.category.UnitTests.class)
    public void test1() {
        System.out.println("xxx");
    }

    @Test
    @Category(com.github.leleact.maven.plugin.surefire.category.OutConnectionTests.class)
    public void test2() {
        System.out.println("yyy");
    }

    @Test
    public void test3() {
        System.out.println("zzz");
    }
}
