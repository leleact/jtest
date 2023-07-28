package com.github.leleact.jtest.spring.retry.service.impl;

import com.github.leleact.jtest.spring.retry.exception.BizException;
import com.github.leleact.jtest.spring.retry.service.MyService;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * my service implement
 *
 * @author leleact
 * @since 2023-07-28
 */
@Service
public class MyServiceImpl implements MyService {
    private final AtomicInteger i = new AtomicInteger(0);

    @Retryable(retryFor = BizException.class, maxAttempts = 3, backoff = @Backoff(delay = 30))
    @Override
    public Integer invoke(Integer in) {
        if (in >= 2) {
            return i.addAndGet(1);
        } else {
            throw new BizException();
        }
    }

    @Recover
    @Override
    public Integer recovery(BizException ex) {
        return i.addAndGet(2);
    }
}
