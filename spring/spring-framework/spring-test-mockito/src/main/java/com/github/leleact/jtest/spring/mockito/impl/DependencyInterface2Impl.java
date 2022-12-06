package com.github.leleact.jtest.spring.mockito.impl;

import com.github.leleact.jtest.spring.mockito.DependencyInterface2;
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
public class DependencyInterface2Impl implements DependencyInterface2 {
    @Override
    public void dependency2() {
        log.info("DependencyInterface2::dependency2");
    }
}
