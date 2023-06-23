package com.github.leleact.jtest.swagger.controller;

import com.github.leleact.jtest.swagger.bean.DemoRequest;
import com.github.leleact.jtest.swagger.bean.DemoResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

/**
 * swagger restful demo controller
 *
 * @author leleact
 * @since 2023-06-23
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class DemoController {

    @Operation(summary = "greet a person", description = "greet a hello to request person name")
    @PostMapping("/greet")
    public DemoResponse greet(@RequestBody @Valid DemoRequest request) {
        DemoResponse response = new DemoResponse();
        response.setGreetContent("hello " + request.getName());
        return response;
    }
}
