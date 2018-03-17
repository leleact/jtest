package com.lele.test.web.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Lele on 2017/3/20.
 */
public class thread_local {

    private static Logger log = LoggerFactory.getLogger(thread_local.class);

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static ThreadLocal<Integer> threadId = new ThreadLocal<Integer>() {

        @Override
        protected Integer initialValue() {
            return atomicInteger.getAndIncrement();
        }
    };

    public static int getThreadId() {
        return threadId.get();
    }

    public static void main(String[] args) {
        log.debug("ID:" + getThreadId());
        Thread t1 = new Thread() {

            @Override
            public void run() {
                log.debug("ID:" + getThreadId());
            }
        };

        Thread t2 = new Thread() {

            @Override
            public void run() {
                log.debug("ID:" + getThreadId());
            }
        };

        t1.start();
        t2.start();

        log.debug("ID:" + getThreadId());
    }
}
