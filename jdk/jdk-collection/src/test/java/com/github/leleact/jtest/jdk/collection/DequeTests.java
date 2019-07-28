package com.github.leleact.jtest.jdk.collection;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class DequeTests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void pollTest() {

        Deque<String> deque = new ArrayDeque<>();

        {
            deque.push("1");
            deque.push("2");

            String str = deque.poll();

            Assert.assertEquals("2", str);
            Assert.assertEquals(1, deque.size());
        }

        {
            deque.pop();
            thrown.expect(NoSuchElementException.class);
            deque.pop();
        }
    }
}
