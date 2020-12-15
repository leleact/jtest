package com.github.leleact.jtest.spring.boot.aop.service;

import com.github.leleact.jtest.spring.boot.aop.annotation.AopEx;

public abstract class AbstractSupperMethod implements ISupperMethod {

    @AopEx(Exception.class)
    public String echo(String str) {
        return "hello, " + str;
    }
}
