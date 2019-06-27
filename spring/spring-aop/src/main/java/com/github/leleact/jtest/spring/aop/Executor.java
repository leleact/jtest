package com.github.leleact.jtest.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class Executor implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public String execute1(String arg1, int arg2) {
        String str = arg1 + "," + arg2;
        log.debug("str:[" + str + "]");
        return str;
    }

    public String execute2(String args) {
        log.info(args);
        return "hello " + args;
    }

    @PostConstruct
    public void postConstructor() {
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            log.info("{} : {}", beanName, applicationContext.getBean(beanName));
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
