package com.github.leleact.jtest.spring.test.service.impl;

import com.github.leleact.jtest.spring.test.repository.HelloRepository;
import com.github.leleact.jtest.spring.test.service.HelloService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * hello service implements.
 *
 * @author leleact
 * @since 1.0
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Resource
    private HelloRepository helloRepository;

    @Override
    public String hello(String name) {
        return "real " + helloRepository.getPrefix() + " " + name;
    }
}
