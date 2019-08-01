package com.github.leleact.jtest.spring.ioc.anno;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Teacher implements Person {

    private static final Logger log = LoggerFactory.getLogger(Teacher.class);

    @Override
    public void say() {
        log.info("Teacher say");
    }
}
