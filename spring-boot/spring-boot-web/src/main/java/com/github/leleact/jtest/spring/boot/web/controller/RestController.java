package com.github.leleact.jtest.spring.boot.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @PostMapping("/hello")
    public String responseOk(@RequestBody String name) {
        return "hello " + name;
    }

}
