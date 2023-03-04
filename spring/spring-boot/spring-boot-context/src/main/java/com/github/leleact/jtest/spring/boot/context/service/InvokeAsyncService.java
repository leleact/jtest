package com.github.leleact.jtest.spring.boot.context.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InvokeAsyncService {

    @Resource
    private AsyncService asyncService;

    public String echo(String str) {
        asyncService.echo(str);
        return "hello " + str;
    }
}
