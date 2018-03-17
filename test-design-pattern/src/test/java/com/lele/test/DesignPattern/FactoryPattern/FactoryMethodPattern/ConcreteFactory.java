package com.lele.test.DesignPattern.FactoryPattern.FactoryMethodPattern;

/**
 * Created by Lele on 2017/6/25.
 */
public class ConcreteFactory implements Factory {

    @Override
    public Product factoryMethod1() {
        return new Product1();
    }

    public Product factoryMethod2() {
        return new Product2();
    }
}
