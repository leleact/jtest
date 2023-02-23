package com.github.leleact.jtest.spring.ioc.anno;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ResourceTests.class })
@Configuration
public class ResourceTests {

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

    // Resource 按照名称匹配，如果名称不存在，按照类型匹配，优先匹配Primary 如果没有Primary且有两个bean， 会报错
    @Resource
    private TestHelloInterface testHello1;

    @Test
    public void hello1() {

        String s = testHello1.hello("111");

        Assertions.assertEquals("Hello 111 1", s);

    }

    // Resource 按照名称匹配，如果名称不存在，按照类型匹配，优先匹配Primary 如果没有Primary且有两个bean， 会报错
    @Resource
    private TestHelloInterface testHello2;

    @Test
    public void hello2() {
        String s = testHello2.hello("111");
        Assertions.assertEquals("Hello 111 2", s);
    }

}
