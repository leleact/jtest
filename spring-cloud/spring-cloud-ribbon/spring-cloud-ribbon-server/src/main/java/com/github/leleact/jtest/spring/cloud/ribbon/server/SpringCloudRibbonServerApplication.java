package com.github.leleact.jtest.spring.cloud.ribbon.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class SpringCloudRibbonServerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudRibbonServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudRibbonServerApplication.class, args);
    }

    @RequestMapping(value = "/hello")
    public String hello(@RequestBody String name) {
        LOGGER.info("receive name : {} ", name);
        return "hello, " + name;
    }
}
