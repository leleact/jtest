package com.github.leleact.jtest.dubbo.provider.dubbo.service.impl;

import com.github.leleact.jtest.dubbo.api.EchoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

@Slf4j
@DubboService
public class EchoServiceImpl implements EchoService {

    @Override
    public Object echo(Object message) {
        log.info("receive message: {}", message);
        try {
            Thread.sleep(30000L);
        } catch (InterruptedException e) {
            log.info("Interrupted thread.", e);
        }
        return message;
    }
}
