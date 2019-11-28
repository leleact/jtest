package com.github.leleact.jtest.dubbo.consumer;

import com.github.leleact.jtest.dubbo.api.EchoService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DubboConsumerApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(DubboConsumerApplicationTests.class);

    @Reference
    private EchoService echoService;

    @Test
    public void contextTest() {
        log.info("test start");
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < 992; n++) {
            sb.append("1");
        }
        int i = 0;
//        while (i++ < 1000000000) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Object o = echoService.echo(uuid + sb.toString());
        log.info("receive {}", o.toString());
//        }

    }
}
