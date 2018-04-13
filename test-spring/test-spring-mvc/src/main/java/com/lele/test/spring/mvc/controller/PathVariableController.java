package com.lele.test.spring.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathVariableController {

    private static final Logger log = LoggerFactory.getLogger(PathVariableController.class);

    @RequestMapping(value = "/{id}/info")
    public String getInfo(@PathVariable("id") String id) {
        log.info("id: {}", id);
        return "ok";
    }

}
