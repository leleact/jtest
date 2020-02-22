package com.github.leleact.jtest.spring.boot.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * same url different action.
 *
 * @author Lele
 * @since 1.0
 */
@RestController
@RequestMapping("/same_url")
public class SameUrlController {

    @PostMapping
    public String create(@RequestBody String str) {
        return "same_url " + str;
    }

    @GetMapping(value = {"/{str}", "/"})
    public String query(@PathVariable(name = "str", required = false) String str) {
        return "same_url " + str;
    }
}
