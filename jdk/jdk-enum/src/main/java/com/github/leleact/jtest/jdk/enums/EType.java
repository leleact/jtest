package com.github.leleact.jtest.jdk.enums;

/**
 * enum string type.
 *
 * @author leleact
 * @since 1.0
 */
public enum EType {

    A("a"),

    B("b"),

    C("c");

    private final String value;

    EType(String value) {
        this.value = value;
    }

    public final String value() {
        return this.value;
    }

    public static EType constantOf(String value) {
        for (EType e : EType.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("value [" + value + "] not illegal");
    }
}
