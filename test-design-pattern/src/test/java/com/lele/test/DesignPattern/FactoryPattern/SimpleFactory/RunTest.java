package com.lele.test.DesignPattern.FactoryPattern.SimpleFactory;

import org.junit.Test;

/**
 * 简单工厂模式
 */
public class RunTest {

    @Test
    public void simpleFactoryTest() {
        {
            SimpleProductFactory factory = new SimpleProductFactory();
            int type = 1;
            Product p = factory.getProduct(type);
            p.show();
        }

        {
            SimpleProductFactory factory = new SimpleProductFactory();
            int type = 2;
            Product p = factory.getProduct(type);
            p.show();

            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

            for (StackTraceElement stackTraceElement : stackTraceElements) {

                System.out.println("===============================================");
                System.out.println("methodName[" + stackTraceElement.getMethodName() + "]");
                System.out.println("className[" + stackTraceElement.getClassName() + "]");
                System.out.println("fileName[" + stackTraceElement.getFileName() + "]");
                System.out.println("lineNumber[" + stackTraceElement.getLineNumber() + "]");
                System.out.println("===============================================");

            }
        }
    }
}
