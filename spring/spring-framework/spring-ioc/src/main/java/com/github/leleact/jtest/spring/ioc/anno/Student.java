package com.github.leleact.jtest.spring.ioc.anno;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ss")
public class Student implements Person {

    private static final Logger log = LoggerFactory.getLogger(Student.class);

    @Override
    public void say() {
        log.info("Student say");
    }
}
