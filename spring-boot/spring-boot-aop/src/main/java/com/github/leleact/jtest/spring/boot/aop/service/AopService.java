package com.github.leleact.jtest.spring.boot.aop.service;

import com.github.leleact.jtest.spring.boot.aop.annotation.AopEx;
import org.springframework.stereotype.Service;

@Service
public class AopService {
    @AopEx
    public String execute(String str) {
        return "hello, " + str;
    }
}
