package com.github.leleact.jtest.spring.boot.web.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionHandleController {

    @GetMapping(value = "/{id}/info")
    public String getInfo(@PathVariable("id") String id) {
        Integer i = Integer.parseInt(id);
        if (i % 2 == 0) {
            return id + "-info";
        } else {
            return id + "+info";
        }
    }

    @GetMapping("/rex")
    public String exceptionthrow() {
        throw new RuntimeException();
    }

    @ExceptionHandler({NumberFormatException.class})
    public String numberFormatException() {
        return "NumberFormatException";
    }

}
