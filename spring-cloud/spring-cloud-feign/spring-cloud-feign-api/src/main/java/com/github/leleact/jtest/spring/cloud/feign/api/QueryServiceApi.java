package com.github.leleact.jtest.spring.cloud.feign.api;

import com.github.leleact.jtest.spring.cloud.feign.api.request.QueryRequest;
import com.github.leleact.jtest.spring.cloud.feign.api.response.QueryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "queryServiceApi", value = "feign-server")
public interface QueryServiceApi {

    @GetMapping("/people/{age}")
    QueryResponse<String> query(@PathVariable("age") Long age, @RequestParam("page") Integer page, @SpringQueryMap QueryRequest request);
}
