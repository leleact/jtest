package com.github.leleact.jtest.spring.cloud.feign.client;

import com.github.leleact.jtest.spring.cloud.feign.api.EchoServiceApi;
import com.github.leleact.jtest.spring.cloud.feign.api.QueryServiceApi;
import com.github.leleact.jtest.spring.cloud.feign.api.WaitServiceApi;
import com.github.leleact.jtest.spring.cloud.feign.api.request.QueryRequest;
import com.github.leleact.jtest.spring.cloud.feign.api.response.QueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class FeignClientApplicationTests {

    @Autowired
    private EchoServiceApi echoServiceApi;

    @Autowired
    private WaitServiceApi waitServiceApi;

    @Autowired
    private QueryServiceApi queryServiceApi;

    @Test
    public void echoServiceTest() {
        String res = echoServiceApi.echo("xxx");
        log.info("res: [{}]", res);
        Assertions.assertEquals("hello xxx", res);
    }

    @Test
    public void waitServiceTest() {
        log.info("start...");
        String res = waitServiceApi.waitAtTime(80000);
        log.info("res: [{}]", res);
        Assertions.assertEquals("hello, world", res);
    }

    @Test
    public void queryTest() {
        log.info("start...");
        QueryRequest request = new QueryRequest();
        request.setPage(1);
        request.setRows(10);
        QueryResponse<String> res = queryServiceApi.query(10L, 2, request);
        log.info("res: [{}]", res);
        Assertions.assertEquals(2, res.getEList().size());
    }
}
