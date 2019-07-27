package com.github.leleact.jtest.dubbo.compatibility.provider.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lele.test.dubbo.compatibility.api.EchoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EchoServiceImpl implements EchoService {

    private static final Logger log = LoggerFactory.getLogger(EchoServiceImpl.class);

    @Override
    public String echo(String message) {
        log.info("receive message: {}", message);
        return message;
    }
}
