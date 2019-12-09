package com.github.leleact.jtest.spring.boot.context.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private static final Logger log = LoggerFactory.getLogger(MyBeanFactoryPostProcessor.class);

    public MyBeanFactoryPostProcessor() {
        log.info("==================  constructor ================");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("==============={}==============", beanFactory);
    }
}
