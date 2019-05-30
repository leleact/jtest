package com.lele.test.jdk.concurrency;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTest {

    private static Logger log = LoggerFactory.getLogger(MainTest.class);

    public static class Param {
        private int a = 1;
        private int b = 2;

        void foo() {
            a = 3;
            b = 4;
        }

        int getA() {
            return a;
        }

        int getB() {
            return b;
        }
    }

    @Test
    public void reOrderTest() {

        Param p = new Param();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                p.foo();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("a=[" + p.getA() + "]");
                log.info("b=[" + p.getB() + "]");
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void reOrderTestTimes() {
        for (int i = 0; i < 100; i++) {
            reOrderTest();
            log.info("" + i + " times!!!\n\n\n");
        }
    }

}
