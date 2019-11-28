package com.github.leleact.jtest.dubbo.provider.controller;

import org.apache.dubbo.registry.support.AbstractRegistryFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterConfigController {

    @GetMapping("/stop")
    public String stop() {
        AbstractRegistryFactory.destroyAll();
        return "ok";
    }
}
