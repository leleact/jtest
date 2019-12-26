package com.github.leleact.jtest.spring.boot.context.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncService implements InitializingBean {

    @Async
    public void echo(String str) {
        log.debug("str: [{}]", str);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("xx");
    }
}
