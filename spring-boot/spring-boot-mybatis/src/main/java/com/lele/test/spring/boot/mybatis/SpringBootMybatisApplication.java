package com.lele.test.spring.boot.mybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMybatisApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringBootMybatisApplication.class);

    public static void main(String[] args) {

        log.info("spring boot start");

        SpringApplication.run(SpringBootMybatisApplication.class, args);
    }
}
