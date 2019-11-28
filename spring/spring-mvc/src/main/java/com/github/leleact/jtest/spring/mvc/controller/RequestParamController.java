package com.github.leleact.jtest.spring.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "request")
public class RequestParamController {

    @GetMapping(value = "/echo")
    public String echo1(@RequestParam long sleepTime, @RequestBody String content) {
        log.info("sleep time: [{}], content: [{}]", sleepTime, content);
        return "hello1 " + content;
    }

    @PostMapping(value = "/echo")
    public String echo2(@RequestParam long sleepTime, @RequestBody String content) {
        log.info("sleep time: [{}], content: [{}]", sleepTime, content);
        return "hello2 " + content;
    }

}
