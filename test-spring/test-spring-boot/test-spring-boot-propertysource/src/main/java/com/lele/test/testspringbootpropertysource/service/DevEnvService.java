package com.lele.test.testspringbootpropertysource.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Profile("dev")
public class DevEnvService {

    private static final Logger log = LoggerFactory.getLogger(DevEnvService.class);

    @PostConstruct
    public void constructor() {
        log.info("test environment");
    }
}
