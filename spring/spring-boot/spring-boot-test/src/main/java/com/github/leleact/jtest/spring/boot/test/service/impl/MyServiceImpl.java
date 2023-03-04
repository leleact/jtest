package com.github.leleact.jtest.spring.boot.test.service.impl;

import com.github.leleact.jtest.spring.boot.test.service.MyDependency1Service;
import com.github.leleact.jtest.spring.boot.test.service.MyDependency2Service;
import com.github.leleact.jtest.spring.boot.test.service.MyService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


/**
 * my service implement
 *
 * @author leleact
 * @since 2022-12-10
 */
@Service
public class MyServiceImpl implements MyService {
    @Resource
    private MyDependency1Service dependency1Service;

    @Resource
    private MyDependency2Service dependency2Service;

    @Override
    public int getCount(String str1, String str2) {
        return dependency1Service.getDepCount(str1) + dependency2Service.getDepCount(str2);
    }
}
