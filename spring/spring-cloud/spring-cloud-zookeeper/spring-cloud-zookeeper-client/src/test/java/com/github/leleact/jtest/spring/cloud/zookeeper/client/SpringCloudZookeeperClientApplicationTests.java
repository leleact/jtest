package com.github.leleact.jtest.spring.cloud.zookeeper.client;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest
@Slf4j
public class SpringCloudZookeeperClientApplicationTests {

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Test
    public void restTemplateTest() {
        for (int i = 0; i < 10000; i++) {
            log.info("start...");
            String str = restTemplate.postForObject("http://zookeeper-server/echo?sleepTime=6000", "abc", String.class);
            log.info("end...str: {}", str);
        }
    }

    @Test
    public void serviceUrlTest() {
        List<ServiceInstance> list = discoveryClient.getInstances("zookeeper-server");
        if (list != null && !list.isEmpty()) {
            log.info("uri : {}", list.get(0).getUri().toString());
        }
    }

//    @Test
//    public void ribbonLoadBalanceHttpTest() throws Exception {
//        RibbonCommandContext ctx = new RibbonCommandContext("zookeeper-server", ...);
//        RibbonApacheHttpRequest request = new RibbonApacheHttpRequest(ctx);
//        RibbonApacheHttpResponse response = retryableRibbonLoadBalancingHttpClient.execute(request, null);
//        log.info("response: [{}]", response);
//    }

}
