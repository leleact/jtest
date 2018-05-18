package com.lele.test.jdk.enums;

/**
 * Created by Lele on 2017/7/7.
 */
public enum EType1 {
    A("0"),

    B("1"),

    C("2"),

    D("3");

    private final String value;

    EType1(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
