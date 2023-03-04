package com.github.leleact.jtest.spring.boot.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/servlet-controller")
public class HttpServletRequestController {

    @PostMapping
    public String echo(HttpServletRequest request) {
        return "ok";
    }
}
