package com.github.leleact.jtest.spring.retry.service;

import com.github.leleact.jtest.spring.retry.exception.BizException;

/**
 * My Service
 *
 * @author leleact
 * @since 2023-07-28
 */
public interface MyService {
    Integer invoke(Integer in);

    Integer recovery(BizException ex);
}
