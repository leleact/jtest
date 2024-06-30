package com.github.leleact.jtest.spring.ioc.bean.postProcessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by Lele on 2017/6/30.
 */
@Slf4j
public class BeanPostProcessor2 implements BeanPostProcessor {

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
