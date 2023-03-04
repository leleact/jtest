package com.github.leleact.jtest.spring.cloud.feign.api;

import com.github.leleact.jtest.spring.cloud.feign.api.request.ComplexRequest;
import com.github.leleact.jtest.spring.cloud.feign.api.request.GenericRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(contextId = "genericServiceApi", value = "feign-server")
public interface GenericServiceApi {

    @GetMapping("/generic")
    String generic(@SpringQueryMap GenericRequest<ComplexRequest> request);
}
