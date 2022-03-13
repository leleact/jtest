package com.github.leleact.jtest.spring.cloud.ribbon.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@SpringBootTest
public class SpringCloudRibbonClientApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudRibbonClientApplicationTests.class);

    @Resource
    RestTemplate restTemplate;

    @Test
    public void ribbonTest() {
        String greeting = this.restTemplate.postForObject("http://ribbon-server/hello", "abc", String.class);
        LOGGER.info("result: [{}]", greeting);
        Assertions.assertEquals("hello, abc", greeting);
    }

}
