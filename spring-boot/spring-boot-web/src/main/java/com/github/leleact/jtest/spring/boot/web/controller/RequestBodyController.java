package com.github.leleact.jtest.spring.boot.web.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/requestBody")
public class RequestBodyController {

    @Data
    public static class CommonRequest {
        public CommonRequest() {
            log.debug("CommonRequest construct");
        }

        private String name;
        private Integer age;
    }

    @PostMapping("/common")
    public String debugAnnotationRequestBody(@RequestBody CommonRequest request) {
        log.debug("request: [{}]", request);
        return "hello " + request.getName();
    }
}
