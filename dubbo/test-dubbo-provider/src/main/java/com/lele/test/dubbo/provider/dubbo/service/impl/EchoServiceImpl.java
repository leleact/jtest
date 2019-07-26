package com.lele.test.dubbo.provider.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lele.test.dubbo.api.EchoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service(retries = -1)
public class EchoServiceImpl implements EchoService {

    private static final Logger log = LoggerFactory.getLogger(EchoServiceImpl.class);

    @Override
    public Object echo(Object message) {
        log.info("receive message: {}", message);
        try {
            Thread.sleep(30000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return message;
    }
}
