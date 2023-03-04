package com.github.leleact.jtest.spring.ioc.anno;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:spring/anno/spring-annotation-test.xml"})
@Slf4j
public class AnnoTest implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Resource
    @Qualifier("ss")
    private Person person;

    @Test
    void test1() {
        for (String bdn : applicationContext.getBeanDefinitionNames()) {
            log.info("beanName: {}, class: {}", bdn, applicationContext.getBean(bdn));
            log.info("beanName: {}, aliasName: {}", bdn, applicationContext.getAliases(bdn));
        }
        person.say();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
