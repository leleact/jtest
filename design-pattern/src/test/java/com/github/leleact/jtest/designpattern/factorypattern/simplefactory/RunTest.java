package com.github.leleact.jtest.designpattern.factorypattern.simplefactory;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 简单工厂模式
 */
@Slf4j
class RunTest {

    @Test
    void simpleFactoryTest() {
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

                log.info("===============================================");
                log.info("methodName[" + stackTraceElement.getMethodName() + "]");
                log.info("className[" + stackTraceElement.getClassName() + "]");
                log.info("fileName[" + stackTraceElement.getFileName() + "]");
                log.info("lineNumber[" + stackTraceElement.getLineNumber() + "]");
                log.info("===============================================");

            }
        }
    }
}
