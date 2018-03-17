package com.lele.test.exception;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RunTimeException 不需要throw或者catch
 */

public class ExceptionTest {

    private Logger log = LoggerFactory.getLogger(ExceptionTest.class);

    @Test
    public void uncheckedExceptionTest() {
        try {
            MethodThrowExceptions.methodThrowUncheckException(
                    MethodThrowExceptions.ExceptionType.IndexOutOfBoundsExceptionType);
        } catch (Exception ex) {
            Assert.assertTrue(ex instanceof RuntimeException);
        }
    }

    @Test
    public void checkedExceptionTest() {
        try {
            MethodThrowExceptions.methodThrowCheckEception();
        } catch (Exception ex) {
            Assert.assertTrue(ex instanceof Exception);
        }
    }


    @Test
    public void tryFinallyBlockTest() {
        String res = tryFinallyMethod();
        log.debug(res);
    }


    private String tryFinallyMethod() {

        try {
            log.debug("entry try block");
            return "123";
        } finally {
            log.debug("entry finally block");
            // return "345";
        }

    }
}