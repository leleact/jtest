package com.lele.test.dubbo.compatibility.provider;

import com.lele.test.dubbo.compatibility.api.EchoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoService implements EchoService {

    private static final Logger log = LoggerFactory.getLogger(DemoService.class);

    @Override
    public String echo(String message) {
        log.info("receive message: {}", message);
        return message;
    }
}
