package com.github.leleact.jtest.jdk.finallz;

import org.junit.Assert;
import org.junit.Test;

public class FinallyTester {
    private Integer m() {
        try {
            throw new Exception("");
        } catch (Exception e) {
            return 2;
        } finally {
            return 1;
        }
    }

    @Test
    public void returnTest() {
        int c = m();
        Assert.assertEquals(1, c);
    }
}
