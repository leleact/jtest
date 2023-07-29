package com.github.leleact.jtest.spring.retry.exception;

/**
 * business exception
 *
 * @author leleact
 * @since 2023-07-28
 */
public class BizException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = -6554274737825257159L;

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }
}
