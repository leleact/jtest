package com.github.leleact.jtest.spring.cloud.feign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(contextId = "echoServiceApi", value = "feign-server")
public interface EchoServiceApi {

    @PostMapping("/echo")
    String echo(@RequestBody String str);
}
