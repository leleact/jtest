package com.github.leleact.jtest.spring.cloud.zookeeper.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class SpringCloudZookeeperServerApplication {

    @RequestMapping(value = "/echo")
    public String echo(@RequestBody String str) {
        log.info("request str: {}", str);
        return str;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringCloudZookeeperServerApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
