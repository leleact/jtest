package com.github.leleact.jtest.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring/dubbo.xml")
public class DubboMultipleInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboMultipleInterfaceApplication.class, args);
    }
}
