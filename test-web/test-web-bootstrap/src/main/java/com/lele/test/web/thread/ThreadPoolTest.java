package com.lele.test.web.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new TestRunable());
        }
        executorService.shutdown();
    }
}


class TestRunable implements Runnable {

    static {
        System.out.println("static 被调用了");
    }

    TestRunable() {
        System.out.println("TestRunable Constructor!!!");
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程被调用了。");
    }
}
