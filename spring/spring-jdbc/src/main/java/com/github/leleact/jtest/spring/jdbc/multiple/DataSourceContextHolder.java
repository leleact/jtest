package com.github.leleact.jtest.spring.jdbc.multiple;

public class DataSourceContextHolder {

    private static ThreadLocal<String> dataSourceHolder = new ThreadLocal<>();

    public static void set(String dataSourceName) {
        dataSourceHolder.set(dataSourceName);
    }

    public static String get() {
        return dataSourceHolder.get();
    }

    public static void clear() {
        dataSourceHolder.remove();
    }
}
