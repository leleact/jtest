package com.github.leleact.jtest.spring.mockito;

import com.github.leleact.jtest.spring.mockito.impl.MyInterfaceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * my interface test
 *
 * @author leleact
 * @since 2022-12-05
 */
@SpringJUnitConfig(locations = {"classpath:spring-context-test.xml"})
public class MyInterfaceTests {
    @InjectMocks
    private MyInterfaceImpl myInterface;

    @Mock
    private DependencyInterface1 dependencyInterface1;

    @Mock
    private DependencyInterface2 dependencyInterface2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void myMethodTest() {
        Mockito.mock(DependencyInterface1.class).dependency1();
        Mockito.mock(DependencyInterface2.class).dependency2();
        myInterface.myMethod();
    }
}
