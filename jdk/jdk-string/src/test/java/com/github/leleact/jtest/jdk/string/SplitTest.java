package com.github.leleact.jtest.jdk.string;

import org.junit.Test;

public class SplitTest {

    @Test
    public void splitTest(){

        String s = "ss";
        s.split("[;,\t ]");
    }
}
