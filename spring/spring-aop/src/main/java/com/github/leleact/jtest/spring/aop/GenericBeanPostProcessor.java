package com.github.leleact.jtest.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Slf4j
@Component
public class GenericBeanPostProcessor implements BeanPostProcessor {

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
