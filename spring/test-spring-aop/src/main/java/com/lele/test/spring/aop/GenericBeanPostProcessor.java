package com.lele.test.spring.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public class GenericBeanPostProcessor implements BeanPostProcessor {

    private static final Logger log = LoggerFactory.getLogger(GenericBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Class<?> clazz = bean.getClass();
        if (beanName != null && beanName.equals("excutor")) {
            log.info(beanName);

            Annotation[] ans = clazz.getAnnotations();
            for (Annotation an : ans) {
                log.info(an.toString());
            }
        }

        return bean;
    }
}
