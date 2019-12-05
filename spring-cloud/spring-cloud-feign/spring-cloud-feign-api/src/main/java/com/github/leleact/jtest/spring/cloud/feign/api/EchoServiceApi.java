package com.github.leleact.jtest.spring.cloud.feign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(contextId = "echoServiceApi")
public interface EchoServiceApi {

    @PostMapping("/echo")
    String echo(String str);
}
