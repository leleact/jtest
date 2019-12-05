package com.github.leleact.jtest.spring.cloud.feign.client;

import com.github.leleact.jtest.spring.cloud.feign.api.EchoServiceApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class FeignClientApplicationTests {

    @Autowired
    EchoServiceApi echoServiceApi;

    @Test
    public void echoServiceTest() {
        String res = echoServiceApi.echo("xxx");
        log.info("res: [{}]", res);
        Assertions.assertEquals("hello xxx", res);
    }
}
