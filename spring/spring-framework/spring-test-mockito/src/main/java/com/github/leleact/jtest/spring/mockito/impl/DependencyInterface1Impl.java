package com.github.leleact.jtest.spring.mockito.impl;

import com.github.leleact.jtest.spring.mockito.DependencyInterface1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * DependencyInterface1 implement
 *
 * @author leleact
 * @since 2022-12-05
 */
@Slf4j
@Service
public class DependencyInterface1Impl implements DependencyInterface1 {
    @Override
    public void dependency1() {
        log.info("DependencyInterface1::dependency1");
    }
}
