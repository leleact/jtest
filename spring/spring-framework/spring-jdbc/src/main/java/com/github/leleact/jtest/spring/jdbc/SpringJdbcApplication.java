package com.github.leleact.jtest.spring.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringJdbcApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcApplication.class, args);
    }
}
