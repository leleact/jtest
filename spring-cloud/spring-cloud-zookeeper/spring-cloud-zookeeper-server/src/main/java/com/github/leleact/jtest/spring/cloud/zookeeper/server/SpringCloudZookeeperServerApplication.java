package com.github.leleact.jtest.spring.cloud.zookeeper.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class SpringCloudZookeeperServerApplication {

    @PostMapping(value = "/echo")
    public String echo(@RequestParam long sleepTime, @RequestBody String str) throws InterruptedException {
        log.info("request str: {}", str);
        if (sleepTime > 0) {
            Thread.sleep(sleepTime);
        }
        return str;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringCloudZookeeperServerApplication.class).web(WebApplicationType.SERVLET)
                                                                                 .run(args);
    }
}
