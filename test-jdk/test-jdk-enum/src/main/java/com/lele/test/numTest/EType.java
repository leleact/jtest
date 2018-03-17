package com.lele.test.numTest;

/**
 * Created by Lele on 2017/7/7.
 */
public enum EType {
    A(0),

    B(1),

    C(2),

    D(3);

    private final int value;

    EType(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
