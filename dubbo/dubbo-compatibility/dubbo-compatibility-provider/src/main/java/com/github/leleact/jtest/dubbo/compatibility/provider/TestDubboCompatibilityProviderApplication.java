package com.github.leleact.jtest.dubbo.compatibility.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

@SpringBootApplication
@ImportResource(locations = {"classpath:META-INF/spring/dubbo-provider.xml"})
public class TestDubboCompatibilityProviderApplication {

    private static final Logger log = LoggerFactory.getLogger(TestDubboCompatibilityProviderApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TestDubboCompatibilityProviderApplication.class, args);

        try {
            int c = System.in.read();
            log.info("read {}", c);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}
