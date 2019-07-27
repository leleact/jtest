package com.github.leleact.jtest.dubbo.provider.controller;

import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterConfigController {

    @GetMapping("/stop")
    public String stop() {
        RegistryConfig.destroyAll();
        return "ok";
    }

}
