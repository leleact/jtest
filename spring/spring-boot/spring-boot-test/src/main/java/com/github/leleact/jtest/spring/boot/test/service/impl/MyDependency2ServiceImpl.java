package com.github.leleact.jtest.spring.boot.test.service.impl;

import com.github.leleact.jtest.spring.boot.test.service.MyDependency2Service;
import org.springframework.stereotype.Service;

/**
 * my dependency2 service implement
 *
 * @author leleact
 * @since 2022-12-10
 */
@Service
public class MyDependency2ServiceImpl implements MyDependency2Service {
    @Override
    public int getDepCount(String str) {
        return str.length() -1;
    }
}
