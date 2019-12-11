package com.github.leleact.jtest.spring.boot.aop.service;

import com.github.leleact.jtest.spring.boot.aop.annotation.AopEx;

public interface ISupperMethod {

    @AopEx(Exception.class)
    String echo(String str);
}
