package com.github.leleact.jtest.dubbo.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterConfigController {
    @GetMapping("/stop")
    public String stop() {
        // TODO: AbstractRegistryFactory stop registry
        return "ok";
    }
}
