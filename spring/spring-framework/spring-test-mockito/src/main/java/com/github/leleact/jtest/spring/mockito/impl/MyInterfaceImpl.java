package com.github.leleact.jtest.spring.mockito.impl;

import com.github.leleact.jtest.spring.mockito.DependencyInterface1;
import com.github.leleact.jtest.spring.mockito.DependencyInterface2;
import com.github.leleact.jtest.spring.mockito.MyInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * my interface implement
 *
 * @author leleact
 * @since 2022-12-05
 */
@Slf4j
@Service
public class MyInterfaceImpl implements MyInterface {
    @Autowired
    private DependencyInterface1 dependencyInterface1;

    @Autowired
    private DependencyInterface2 dependencyInterface2;

    @Override
    public void myMethod() {
        dependencyInterface1.dependency1();
        dependencyInterface2.dependency2();
    }
}
