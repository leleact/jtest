package com.github.leleact.jtest.jdk.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

class DequeTests {

    @Test
    void pollTest() {

        Deque<String> deque = new ArrayDeque<>();

        {
            deque.push("1");
            deque.push("2");

            String str = deque.poll();

            Assertions.assertEquals("2", str);
            Assertions.assertEquals(1, deque.size());
        }

        {
            deque.pop();
            Assertions.assertThrows(NoSuchElementException.class, () -> {
                deque.pop();
            });
        }
    }
}
