package com.github.leleact.jtest.spring.boot.test;

import com.github.leleact.jtest.spring.boot.test.service.MyDependency1Service;
import com.github.leleact.jtest.spring.boot.test.service.MyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

/**
 * spring boot test
 *
 * @author leleact
 * @since 2022-12-10
 */
@SpringBootTest
public class SpringTestApplicationTests {
    @Resource
    private MyService myService;

    @MockBean
    private MyDependency1Service myDependency1Service;

    @Test
    public void myServiceCountTest() {
        Mockito.when(myDependency1Service.getDepCount(Mockito.anyString())).thenReturn(10);
        String a = "abc";
        String b = "123";
        Assertions.assertEquals(12, myService.getCount(a, b));
    }
}
