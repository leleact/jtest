package com.github.leleact.jtest.designpattern.factorypattern.factorymethodpattern;

import org.junit.jupiter.api.Test;

class RunTest {

    @Test
    void factoryMethodPatternTest() {

        Factory factory = new ConcreteFactory();

        Product product1 = factory.factoryMethod1();
        Product product2 = factory.factoryMethod2();

        product1.method();
        product2.method();
    }

}
