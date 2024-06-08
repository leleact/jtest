package com.github.leleact.jtest.spring.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "request")
public class RequestParamController {

    // {@code RequestParam} must hava special value
    // Name for argument of type [long] not specified, and parameter name information not available via reflection. Ensure that the compiler uses the '-parameters' flag.
    @GetMapping(value = "/echo")
    public String echo1(@RequestParam("sleepTime") long sleepTime, @RequestBody String content) {
        log.info("sleep time: [{}], content: [{}]", sleepTime, content);
        return "hello1 " + content;
    }

    @PostMapping(value = "/echo")
    public String echo2(@RequestParam("sleepTime") long sleepTime, @RequestBody String content) {
        log.info("sleep time: [{}], content: [{}]", sleepTime, content);
        return "hello2 " + content;
    }
}
