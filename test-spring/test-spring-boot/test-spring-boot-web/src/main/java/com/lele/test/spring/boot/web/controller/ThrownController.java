package com.lele.test.spring.boot.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThrownController {

    @GetMapping("/ex")
    public String exceptionthrow() {
        throw new RuntimeException("");
    }
}
