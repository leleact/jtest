package com.github.leleact.jtest.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * RunTimeException 不需要throw或者catch
 */
@Slf4j
public class ExceptionTest {

    @Test
    public void uncheckedExceptionTest() {
        try {
            MethodThrowExceptions.methodThrowUncheckException(
                MethodThrowExceptions.ExceptionType.IndexOutOfBoundsExceptionType);
        } catch (Exception ex) {
            Assertions.assertTrue(ex instanceof RuntimeException);
        }
    }

    @Test
    public void checkedExceptionTest() {
        try {
            MethodThrowExceptions.methodThrowCheckEception();
        } catch (Exception ex) {
            Assertions.assertTrue(ex instanceof Exception);
        }
    }

    @Test
    public void tryFinallyBlockTest() {
        String res = tryFinallyMethod();
        log.debug(res);
    }

    @Test
    public void staticFiledLinkError() {
        Assertions.assertThrows(ExceptionInInitializerError.class, () -> {
            String path = NotExistFilePath.PATH;
        });
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
