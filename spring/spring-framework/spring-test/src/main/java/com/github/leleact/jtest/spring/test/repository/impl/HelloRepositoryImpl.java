package com.github.leleact.jtest.spring.test.repository.impl;

import com.github.leleact.jtest.spring.test.repository.HelloRepository;
import org.springframework.stereotype.Repository;

/**
 * hello repository implements.
 *
 * @author leleact
 * @since 1.0
 */
@Repository
public class HelloRepositoryImpl implements HelloRepository {
    @Override
    public String getPrefix() {
        return "hello";
    }
}
