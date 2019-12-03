package com.github.leleact.jtest.spring.boot.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/none-mapping-value")
public class NoneMappingValueController {

    @GetMapping
    public String query() {
        return "ok";
    }

}
