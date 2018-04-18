package com.lele.test.spring.boot.urlmap.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class WelcomeController {

    @GetMapping("/wel1")
    public String wel1() {
        return "wel1";
    }

    @GetMapping("/wel2")
    public String wel2() {
        return "wel2";
    }

}
