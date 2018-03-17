package com.lele.test.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("AutoWiredService")
public class AutoWiredService {

    private Logger log = LoggerFactory.getLogger(getClass());

    public void doSomeThing() {
        log.debug("do some thing");
    }
}
