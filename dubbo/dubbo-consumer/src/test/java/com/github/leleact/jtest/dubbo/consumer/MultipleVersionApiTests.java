package com.github.leleact.jtest.dubbo.consumer;

import com.github.leleact.jtest.dubbo.api.MultipleVerApi;
import com.github.leleact.jtest.dubbo.api.response.ListResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MultipleVersionApiTests {

    @Reference
    private MultipleVerApi multipleVerApi;

    @Test
    public void multipleVerApiTest() {
        ListResponse response = multipleVerApi.getResponse();
        log.info("response: [{}]", response);
    }
}
