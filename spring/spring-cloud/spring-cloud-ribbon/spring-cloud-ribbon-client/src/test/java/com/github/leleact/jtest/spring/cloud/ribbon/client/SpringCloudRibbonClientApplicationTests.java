package com.github.leleact.jtest.spring.cloud.ribbon.client;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringCloudRibbonClientApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudRibbonClientApplicationTests.class);

    @Resource
    RestTemplate restTemplate;

    @Test
    @Category(com.github.leleact.jtest.spring.cloud.ribbon.client.Category.OuterTests.class)
    public void ribbonTest() {
        String greeting = this.restTemplate.postForObject("http://ribbon-server/hello", "abc", String.class);
        LOGGER.info("result: [{}]", greeting);
        Assert.assertEquals("hello, abc", greeting);
    }

}
