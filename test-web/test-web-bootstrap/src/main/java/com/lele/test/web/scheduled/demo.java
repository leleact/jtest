package com.lele.test.web.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class demo {

    public demo() {
        log.debug("constructor!!!");
    }

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public int exec() {
        log.debug("xxxx");
        return 100;
    }
}
