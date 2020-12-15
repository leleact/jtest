package com.github.leleact.jtest.spring.boot.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/bind")
public class MapController {

    @PostMapping("/j2m")
    public String json2Map(@RequestBody Map<String, String> map) {
        log.info("map: [{}]", map);
        return "ok";
    }
}
