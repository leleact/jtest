package com.github.leleact.jtest.springboot.propertysource.service;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


@Service
@Profile("test")
public class TestEnvService {

    private static final Logger log = LoggerFactory.getLogger(TestEnvService.class);

    @PostConstruct
    public void constructor() {
        log.info("test environment");
    }
}
