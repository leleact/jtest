package com.github.leleact.jtest.spring.boot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InputOnlyController {

    @GetMapping("/input")
    public String greeting(Model model) {
        return "input";
    }
}
