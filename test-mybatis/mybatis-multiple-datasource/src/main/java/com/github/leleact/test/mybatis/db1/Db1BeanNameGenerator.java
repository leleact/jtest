package com.github.leleact.test.mybatis.db1;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

public class Db1BeanNameGenerator implements BeanNameGenerator {

    public Db1BeanNameGenerator() {
    }

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        return definition.getBeanClassName().substring(0, 1).toLowerCase() + definition.getBeanClassName().substring(
                1) + "1";
    }
}
