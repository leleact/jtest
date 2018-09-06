package com.github.leleact.test.spring.profiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    // springboot log4j2 会新增一个Filter导致日志打印不出来，在基本环境变量加载完成后删除该filter
    // 在 jvm 中设置 -Dspring.profiles.active=uat 后，环境变量中设置 spring_profiles_active=sit
    // 两个都会读取
    // ConfigFileApplicationListener会再load一遍环境变量中的spring_profiles_active [spring boot 1.5.10]
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
