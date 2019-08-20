package com.github.leleact.jtest.springboot.propertysource.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Profile("dev")
public class DevEnvService {

    private static final Logger log = LoggerFactory.getLogger(DevEnvService.class);

    public DevEnvService() {
        log.info("===============");
    }
    // 可以直接从 System property总获取
    @Value("${environmentToken}")
    private String environmentToken;

    @PostConstruct
    public void constructor() {
        log.info("test environment");
        log.info("{}", environmentToken);
    }
}
