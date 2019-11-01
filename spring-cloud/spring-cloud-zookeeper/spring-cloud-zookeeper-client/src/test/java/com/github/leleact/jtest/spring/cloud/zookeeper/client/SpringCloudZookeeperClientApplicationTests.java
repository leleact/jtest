package com.github.leleact.jtest.spring.cloud.zookeeper.client;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
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
        String str = restTemplate.postForObject("http://zookeeper-server/echo", "abc", String.class);
        log.info("str: {}", str);
    }

    @Test
    public void serviceUrlTest() {
        List<ServiceInstance> list = discoveryClient.getInstances("zookeeper-server");
        if (list != null && !list.isEmpty()) {
            log.info("uri : {}", list.get(0).getUri().toString());
        }
    }

}
