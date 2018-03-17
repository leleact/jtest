package com.lele.test.DesignPattern.FactoryPattern.FactoryMethodPattern;

import org.junit.Test;

public class RunTest {


    @Test
    public void factoryMethodPatternTest() {

        Factory factory = new ConcreteFactory();

        Product product1 = factory.factoryMethod1();
        Product product2 = factory.factoryMethod2();

        product1.method();
        product2.method();
    }

}
