package com.github.leleact.jtest.spring.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world example.
 *
 * @author leleact
 * @since 1.0
 */
@RestController
public class HelloController {


    @GetMapping("/hello")
    public String helloWorld(@RequestBody String name) {
        return "hello " + name;
    }
}
