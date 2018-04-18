package com.lele.test.spring.boot.urlmap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirectController {

    @GetMapping("/status")
    public String status() {
        return "running";
    }

}
