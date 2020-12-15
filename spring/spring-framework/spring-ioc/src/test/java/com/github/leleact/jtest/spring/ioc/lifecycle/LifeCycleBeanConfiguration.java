package com.github.leleact.jtest.spring.ioc.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LifeCycleBeanConfiguration {

    @Bean(initMethod = "init", destroyMethod = "destroyMethod")
    public LifeCycleTestBean lifeCycleTestBean() {
        return new LifeCycleTestBean();
    }

}
