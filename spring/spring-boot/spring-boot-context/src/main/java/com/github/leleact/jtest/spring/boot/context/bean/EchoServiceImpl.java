package com.github.leleact.jtest.spring.boot.context.bean;

import org.springframework.stereotype.Service;

@Service
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String str) {
        return "hello " + str;
    }
}
