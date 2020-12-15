package com.github.leleact.jtest.spring.cloud.feign.server;

import com.github.leleact.jtest.spring.cloud.feign.api.request.ComplexRequest;
import com.github.leleact.jtest.spring.cloud.feign.api.request.GenericRequest;
import com.github.leleact.jtest.spring.cloud.feign.api.request.HierarchyRequest;
import com.github.leleact.jtest.spring.cloud.feign.api.request.QueryRequest;
import com.github.leleact.jtest.spring.cloud.feign.api.response.QueryResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Api(tags = "feign")
@Slf4j
@RestController
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
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

    @GetMapping("/generic")
    public String generic(GenericRequest<ComplexRequest> request) {
        log.info("request:{}", request);
        return "ok";
    }

    @ApiOperation(value = "hierarchy", response = String.class, tags = {"feign"})
    @GetMapping("/hierarchy")
    public String hierarchy(HierarchyRequest request) {
        log.info("request:{}", request);
        return "ok";
    }

    public static void main(String[] args) {
        SpringApplication.run(FeignServerApplication.class, args);
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build().pathMapping("/");
    }
}
