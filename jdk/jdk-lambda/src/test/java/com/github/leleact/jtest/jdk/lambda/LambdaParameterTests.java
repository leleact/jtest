package com.github.leleact.jtest.jdk.lambda;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Lambda parameter tests
 *
 * @author leleact
 * @since 2023-05-26
 */
public class LambdaParameterTests {

    interface IAdd {
        int add(int a);
    }

    @Test
    public void localParameterTest() {
        int[] left = {10};
        IAdd exe = a -> left[0] + a;
        left[0] = 20;
        Assertions.assertEquals(30, exe.add(10));
    }
}
