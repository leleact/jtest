package com.github.leleact.jtest.build.spring.boot.jar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestBuildSpringBootJarApplication {

    private static final Logger log = LoggerFactory.getLogger(TestBuildSpringBootJarApplication.class);

    public static void main(String[] args) {

        log.info("运行成功");

        SpringApplication.run(TestBuildSpringBootJarApplication.class, args);
    }
}
