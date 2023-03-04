package com.github.leleact.jtest.spring.ioc;

import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.test.annotation.DirtiesContext.HierarchyMode.CURRENT_LEVEL;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BeanScopeTests.class)
@Configuration
@DirtiesContext
public class BeanScopeTests implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(BeanScopeTests.class);

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static class FooBean {

        public void init() {
            log.info("bean init");
        }


        @PreDestroy
        public void destroy() {
            log.info("bean destroy");
        }
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Scope("prototype")
    public FooBean foo() {
        return new FooBean();
    }

    @Test
    @DirtiesContext(hierarchyMode = CURRENT_LEVEL)
     void whenBeanDestroy() {

        // 每次创建一个，applicationContext不会缓存
        for (int i = 0; i < 1000; i++) {
            log.info("{}", applicationContext.getBean(FooBean.class));
            log.info("个数{}", applicationContext.getBeanNamesForType(FooBean.class).length);
        }

    }

}
