package com.github.leleact.jtest.mybatis.spring.type.handler;

import java.io.Serial;

/**
 * type handler exception.
 *
 * @author leleact
 * @since 2024-08-07
 */
public class TypeHandlerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public TypeHandlerException(String message) {
        super(message);
    }

    public TypeHandlerException(String message, Throwable cause) {
        super(message, cause);
    }
}
