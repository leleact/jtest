package com.github.leleact.jtest.spring.boot.aop;

import com.github.leleact.jtest.spring.boot.aop.advisor.ExHandlerAdvisor;
import com.github.leleact.jtest.spring.boot.aop.pointcut.ExHandlerPointcut;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

    @Bean
    public ExHandlerAdvisor exHandlerAdvisor() {
        return new ExHandlerAdvisor();
    }

    @Bean
    public ExHandlerPointcut exHandlerPointcut() {
        return new ExHandlerPointcut();
    }
}
