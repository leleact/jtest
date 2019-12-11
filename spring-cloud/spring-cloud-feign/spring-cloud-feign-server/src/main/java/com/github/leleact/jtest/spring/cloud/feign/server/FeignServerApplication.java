package com.github.leleact.jtest.spring.cloud.feign.server;

import com.github.leleact.jtest.spring.cloud.feign.api.request.QueryRequest;
import com.github.leleact.jtest.spring.cloud.feign.api.response.QueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Slf4j
@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class FeignServerApplication {

    @PostMapping("/echo")
    public String echo(@RequestBody String str) {
        return "hello " + str;
    }

    @PostMapping("/wait")
    public String waitService(@RequestBody long time) throws InterruptedException {
        Thread.sleep(time);
        return "hello, world";
    }

    @GetMapping("/people/{age}")
    public QueryResponse<String> query(
        @PathVariable("age") Long age, @RequestParam("page") Integer page, QueryRequest request) {
        log.info("PathVariable[age]={}", age);
        log.info("RequestParam[page]={}", page);
        log.info("request:{}", request);
        QueryResponse<String> response = new QueryResponse<>();
        response.setEList(Arrays.asList("a", "b"));
        return response;
    }

    public static void main(String[] args) {
        SpringApplication.run(FeignServerApplication.class, args);
    }
}
