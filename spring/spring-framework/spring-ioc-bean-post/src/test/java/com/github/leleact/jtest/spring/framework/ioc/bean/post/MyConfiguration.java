package com.github.leleact.jtest.spring.framework.ioc.bean.post;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * configuration.
 *
 * @author leleact
 * @since 2025-06-07
 */
@Configuration
public class MyConfiguration {

    @Bean("bean")
    public MyBean myBean(MyBean1 myBean1) {
        return new MyBean(myBean1);
    }

    @Bean("bean1")
    public MyBean1 myBean1(@Lazy MyBean myBean) {
        return new MyBean1(myBean);
    }
}
