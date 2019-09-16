package com.github.leleact.jtest.spring.boot.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XmlController {

    private static final Logger log = LoggerFactory.getLogger(XmlController.class);

    @RequestMapping("/xml")
    public String xml(@RequestBody String xml, @RequestBody byte[] bytes) {
        log.info("receive:[{}]", xml);

        log.info("byte: [{}]", new String(bytes));
        return "0000";
    }
}
