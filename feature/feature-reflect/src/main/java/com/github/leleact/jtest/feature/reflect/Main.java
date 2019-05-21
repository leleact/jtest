package com.github.leleact.jtest.feature.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * main entry.
 *
 * @author leleact
 * @since 1.0
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        final int count = 10000;

        Executor executor = new Executor();

        directExecute(executor, count);

        reflectExecute(executor, count);
    }

    private static void directExecute(Executor executor, int count) throws InterruptedException {
        executor.execute();
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            executor.execute();
        }
        System.out.println("direct execute time: " + (System.currentTimeMillis() - start) + " ms");
    }

    private static void reflectExecute(Executor executor, int count) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = Executor.class.getMethod("execute");
        method.invoke(executor);

        long start = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            method.invoke(executor);
        }
        System.out.println("reflect execute time: " + (System.currentTimeMillis() - start) + " ms");
    }
}
