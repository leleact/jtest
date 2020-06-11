package com.github.leleact.jtest.spring.cloud.feign.api;

import com.github.leleact.jtest.spring.cloud.feign.api.request.HierarchyRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(contextId = "hierarchyServiceApi", value = "feign-server")
public interface HierarchyServiceApi {

    @GetMapping("/hierarchy")
    String hierarchy(@SpringQueryMap(encoded = true) HierarchyRequest request);
}
