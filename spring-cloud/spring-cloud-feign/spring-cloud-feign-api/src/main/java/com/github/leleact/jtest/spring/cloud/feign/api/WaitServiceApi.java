package com.github.leleact.jtest.spring.cloud.feign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(contextId = "waitServiceApi", value = "feign-server")
public interface WaitServiceApi {

    @PostMapping("/wait")
    String waitAtTime(@RequestBody long time);
}
