package com.lele.test.validator.bean;

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
}
