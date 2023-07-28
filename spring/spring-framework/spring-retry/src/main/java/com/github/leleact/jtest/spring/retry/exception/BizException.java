package com.github.leleact.jtest.spring.retry.exception;

/**
 * business exception
 *
 * @author leleact
 * @since 2023-07-28
 */
public class BizException extends RuntimeException {
    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }
}
