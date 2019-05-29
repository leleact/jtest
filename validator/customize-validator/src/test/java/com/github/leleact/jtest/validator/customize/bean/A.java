package com.github.leleact.jtest.validator.customize.bean;

import com.github.leleact.jtest.validator.customize.constraints.Enum;

import javax.validation.constraints.NotEmpty;

public class A {

    @NotEmpty(message = "xxx")
    private String S;

    @Enum( enumClass = E.class)
    private String e;

    public String getS() {
        return S;
    }

    public void setS(String s) {
        S = s;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }
}
