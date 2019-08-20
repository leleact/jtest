package com.github.leleact.jtest.spring.mvc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

    @PostMapping("/echo")
    public String echo(@RequestBody String name) {
        return "Hello " + name;
    }

}
