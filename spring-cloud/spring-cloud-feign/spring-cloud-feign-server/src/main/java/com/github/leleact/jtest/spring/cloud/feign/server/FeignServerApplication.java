package com.github.leleact.jtest.spring.cloud.feign.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class FeignServerApplication {

    @PostMapping("/echo")
    public String echo(@RequestBody String str) {
        return "hello " + str;
    }

    public static void main(String[] args) {
        SpringApplication.run(FeignServerApplication.class, args);
    }
}
