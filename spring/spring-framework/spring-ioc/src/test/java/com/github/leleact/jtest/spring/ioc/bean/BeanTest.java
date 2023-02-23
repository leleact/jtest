package com.github.leleact.jtest.spring.ioc.bean;

import com.github.leleact.jtest.spring.ioc.bean.serviceComponet.FooComponent;
import com.github.leleact.jtest.spring.ioc.bean.serviceComponet.FooService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
class BeanTest {

    @Test
    void Application() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:/spring/bean/spring-beans.xml");
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String s : beanNames) {
            log.debug("Bean Name: {}", s);
        }
    }

    // 多个Bean后处理顺序
    @Test
    void beanPostProcessorTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:/spring/beanPostProcessor/spring-beans.xml");
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String s : beanNames) {
            log.debug("Bean Name: {}", s);
        }
    }

    @Test
    void serviceComponentTest() throws ClassNotFoundException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:/spring/bean/spring-service-component.xml");
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String s : beanNames) {
            log.debug("Bean Name: {}", s);
        }

        {
            String name = "com.github.leleact.jtest.spring.ioc.bean.serviceComponet.FooComponent";
            String beanId = (AnnotationUtils.getAnnotation(Class.forName(name), Component.class)).value();
            log.info("beanId=" + beanId);
            if (beanId != null && beanId.trim().length() != 0) {
                FooComponent fooComponent = (FooComponent) applicationContext.getBean(beanId);
            } else {
                log.info("找不到这样的bean");
            }
        }

        {
            String name = "com.github.leleact.jtest.spring.ioc.bean.serviceComponet.FooService";
            String beanId = (AnnotationUtils.getAnnotation(Class.forName(name), Component.class)).value();
            if (beanId != null && beanId.trim().length() != 0) {
                FooService fooService = (FooService) applicationContext.getBean(beanId);
            } else {
                log.info("找不到这样的bean");
            }
        }
    }

    @Test
    void beanMapTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:/spring/bean/spring-mapBean.xml");
        MapBean mapBean = (MapBean) applicationContext.getBean("mapBean");
        Map<String, String> map = mapBean.getStringMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            log.debug("key[" + entry.getKey() + "], value[" + entry.getValue() + "]");
        }
        log.debug("[" + map.get("e") + "]");
    }
}
