package com.github.leleact.jtest.jdk.finallz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Assertions.assertEquals(1, c);
    }
}
