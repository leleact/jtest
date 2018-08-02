package com.lele.test.jdk.lambda;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunctionCallTests {

    private static final Logger logger = LoggerFactory.getLogger(FunctionCallTests.class);

    class A {

        private String str;

        public A(String str) {
            this.str = str;
        }

        public void execute(Caller caller) {
            caller.call(str);
        }
    }

    @Test
    public void callTest() {

        A a = new A("Hello Word!!!");

        a.execute((str) -> {
            logger.info(str);
        });
    }
}
