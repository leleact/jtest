package com.lele.test.jdk.enums;

/**
 * Created by Lele on 2017/7/7.
 */
public enum EType {
    A(0),

    B(10),

    C(2),

    D(3),

    E(3);

    private final int value;

    EType(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    public static EType valueOf(final int value) {
        for (EType e : EType.values()) {
            if (value == e.value) {
                return e;
            }
        }
        return null;
    }
}
