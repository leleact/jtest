package com.github.leleact.jtest.validator.customize.enums;

public enum E {

    AB("00"),
    AC("10");

    private String value;

    E(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public final String value() {
        return value;
    }

    public static E constantOf(final String value) {
        for (E state : E.values()) {
            if (state.value.equals(value)) {
                return state;
            }
        }
        throw new IllegalArgumentException("illegal value[" + value + "]");
    }
}
