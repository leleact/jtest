package com.lele.test.spring.ioc.anno;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:spring/anno/spring-annotation-test.xml"})
public class AnnoTest implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(AnnoTest.class);

    private ApplicationContext applicationContext;

    @Resource
    @Qualifier("ss")
    private Person person;

    @Test
    public void test1() {
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
