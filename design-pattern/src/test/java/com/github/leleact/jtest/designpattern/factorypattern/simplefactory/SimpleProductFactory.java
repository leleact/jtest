package com.github.leleact.jtest.designpattern.factorypattern.simplefactory;


public class SimpleProductFactory {

    public Product getProduct(int type) {
        if (type == 1)
            return new Product1();
        else if (type == 2)
            return new Product2();

        return null;
    }
}
