package com.github.leleact.jtest.spring.boot.test.service.impl;

import com.github.leleact.jtest.spring.boot.test.service.MyDependency1Service;
import org.springframework.stereotype.Service;

/**
 * my dependency1 service implement
 *
 * @author leleact
 * @since 2022-12-10
 */
@Service
public class MyDependency1ServiceImpl implements MyDependency1Service {
    @Override
    public int getDepCount(String str) {
        return str.length() + 1;
    }
}
