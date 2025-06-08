package com.github.leleact.jtest.spring.framework.ioc.bean.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * my bean processor.
 *
 * @author leleact
 * @since 2025-06-07
 */
@Slf4j
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("beanName: {}", beanName);
        return bean;
    }
}
