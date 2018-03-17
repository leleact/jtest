package com.lele.test.numTest;

/**
 * Created by Lele on 2017/7/7.
 */
public enum EType1 {
    A("0"),

    B("1"),

    C("2"),

    D("3");

    private final String value_;

    EType1(String value) {
        this.value_ = value;
    }

    public String value() {
        return this.value_;
    }
}
