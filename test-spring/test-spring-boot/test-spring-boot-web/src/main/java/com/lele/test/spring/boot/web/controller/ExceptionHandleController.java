package com.lele.test.spring.boot.web.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/exh")
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

    @GetMapping("/ex")
    public String exceptionthrow() {
        throw new RuntimeException();
    }

    @ExceptionHandler({NumberFormatException.class,Exception.class})
    public String databaseError() {
        return "NumberFormatException";
    }

}
