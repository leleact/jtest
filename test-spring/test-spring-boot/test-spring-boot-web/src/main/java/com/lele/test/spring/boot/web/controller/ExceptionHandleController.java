package com.lele.test.spring.boot.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler({NumberFormatException.class})
    public String databaseError() {
        return "NumberFormatException";
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, String> handleBadRequest(HttpServletRequest req, Exception ex) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "2");
        return map;
    }

}
