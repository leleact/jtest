package com.lele.test.web.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest implements Callable<Integer> {

    private Integer mValue;

    public CallableTest(Integer mValue) {
        this.mValue = mValue;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(10000);
        System.out.println("ID:" + Thread.currentThread().getId());
        return mValue;
    }


    public static void main(String[] args) {
        CallableTest testor = new CallableTest(10);
        FutureTask<Integer> futureTask = new FutureTask<Integer>(testor);
        Thread t = new Thread(futureTask);
        t.start();

        try {
            System.out.println("ID:" + Thread.currentThread().getId() + " value:" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
