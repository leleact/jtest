package com.lele.test.dubbo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lele.test.dubbo.api.EchoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDubboConsumerApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(TestDubboConsumerApplicationTests.class);

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
        while (i++ < 1000000000) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            Object o = echoService.echo(uuid + sb.toString());
            log.info("receive {}", o.toString());
        }

    }
}
