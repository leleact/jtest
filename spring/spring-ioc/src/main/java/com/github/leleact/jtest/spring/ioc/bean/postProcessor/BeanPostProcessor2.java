package com.github.leleact.jtest.spring.ioc.bean.postProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by Lele on 2017/6/30.
 */
public class BeanPostProcessor2 implements BeanPostProcessor {
    private static Logger log = LoggerFactory.getLogger(BeanPostProcessor2.class);

    @Autowired
    private BeanPostProcessor1 beanPostProcessor1;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.debug("postProcessBeforeInitialization {}", bean.getClass());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.debug("postProcessAfterInitialization {}", bean.getClass());
        return bean;
    }
}
