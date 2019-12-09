package com.github.leleact.jtest.spring.boot.context.configuration;

import com.github.leleact.jtest.spring.boot.context.bean.Person;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration implements ApplicationContextAware  {

    private ApplicationContext applicationContext;

    // doCreateBean
    // mbd.getFactoryMethodName() != null
    // instantiateUsingFactoryMethod
    @Bean("p")
    public Person p() {
        return new Person();
    }

    // Bean named P created by p1() will override bean created by p()
    @Bean("p")
    public Person p1() {
        return new Person();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
