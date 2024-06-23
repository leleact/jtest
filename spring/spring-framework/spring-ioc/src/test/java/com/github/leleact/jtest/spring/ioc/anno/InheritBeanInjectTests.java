package com.github.leleact.jtest.spring.ioc.anno;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@Slf4j
@SpringJUnitConfig(classes = InheritBeanInjectTests.class)
@Configuration
public class InheritBeanInjectTests {
    @Autowired
    private ApplicationContext applicationContext;

    public static class T1 {
    }

    public static class T2 {
    }

    public interface It<T> {

    }

    @Component
    public static class It1 implements It<T1> {
    }

    @Component
    public static class It2 implements It<T2> {
    }

    public static class Ct<T> {

    }

    @Getter
    public static abstract class AbsCt<T> extends Ct<T> {
        // @Autowired
        // private ApplicationContext app;

        @Autowired
        protected It<T> it;

        // protected void show() {
        //   It<T> it = app.getBean(It.class);
        // }
    }

    @Service
    public static class Ct1 extends AbsCt<T1> {
        public Ct1() {
            log.info("ct1 constructor");
        }
    }

    @Service
    public static class Ct2 extends AbsCt<T2> {

        public Ct2() {
            log.info("ct2 constructor");
        }
    }

    @Test
    public void test1() {
        Ct1 c1 = applicationContext.getBean(Ct1.class);
        Ct2 c2 = applicationContext.getBean(Ct2.class);
        Assertions.assertEquals(applicationContext.getBean(It1.class), c1.getIt());
        Assertions.assertEquals(applicationContext.getBean(It2.class), c2.getIt());
    }
}
