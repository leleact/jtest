package com.github.leleact.jtest.jdk.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SplitTest {

    @Test
    public void splitTest() {
        String s = "ss";
        String[] res = s.split("[;,\t ]");
        Assertions.assertEquals(1, res.length);
    }
}
