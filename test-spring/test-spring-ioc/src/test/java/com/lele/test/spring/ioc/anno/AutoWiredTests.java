package com.lele.test.spring.ioc.anno;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AutoWiredTests.class})
@Configuration
public class AutoWiredTests {

    private static final Logger log = LoggerFactory.getLogger(AutoWiredTests.class);

    interface TestHelloInterface {
        String hello(String word);
    }

    @Service("testHello1")
    public class TestHelloInterfaceImpl1 implements TestHelloInterface {
        @Override
        public String hello(String word) {
            return "Hello " + word + " 1";
        }
    }

    @Service
    @Primary
    public class TestHelloInterfaceImpl2 implements TestHelloInterface {
        @Override
        public String hello(String word) {
            return "Hello " + word + " 2";
        }
    }


    // Autowired 按照类型匹配, 如果有两个同类型Bean，优先匹配Primary 如果没有Primary且有两个bean， 会报错
    @Autowired
    private TestHelloInterface testHello1;

    @Test
    public void hello1() {

        String s = testHello1.hello("111");

        Assert.assertEquals("Hello 111 2", s);

    }


    @Autowired
    @Qualifier("testHello1") // 选 interfaceImpl1
    private TestHelloInterface testHello;

    @Test
    public void hello2() {

        String s = testHello.hello("111");

        Assert.assertEquals("Hello 111 1", s);
    }

}
