package com.github.leleact.jtest.dubbo.consumer;

import com.github.leleact.jtest.dubbo.api.MultipleVerApi;
import com.github.leleact.jtest.dubbo.api.response.ListResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
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
