package com.github.leleact.jtest.mybatis.spring.type.handler;

/**
 * type handler exception.
 *
 * @author leleact
 * @since 2024-08-07
 */
public class TypeHandlerException extends RuntimeException {

    public TypeHandlerException(String message) {
        super(message);
    }

    public TypeHandlerException(String message, Throwable cause) {
        super(message, cause);
    }
}
