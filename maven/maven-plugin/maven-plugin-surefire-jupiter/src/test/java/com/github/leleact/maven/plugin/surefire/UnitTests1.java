package com.github.leleact.maven.plugin.surefire;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class UnitTests1 {

    @Test
    @Tag("unittest")
    void test1() {
        System.out.println("xxx");
    }

    @Test
    @Tag("slow")
    void test2() {
        System.out.println("yyy");
    }

    @Test
    @Tag("unittest")
    @Tag("slow")
    void test3() {
        System.out.println("zzz");
    }
}
