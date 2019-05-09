package com.lele.test.spring.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Excutor implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(Excutor.class);

    private ApplicationContext applicationContext;

    public String excute1(String arg1, int arg2) {
        String str = arg1 + "," + arg2;
        log.debug("str:[" + str + "]");
        return str;
    }

    public String excute2(String args) {
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
