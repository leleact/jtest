package com.github.leleact.test.spring.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @RequestMapping("/map")
    public Map<String, String> echo(HttpServletRequest request, @RequestParam Map<String, String> map) throws
            IOException {

        logger.info("request map: [{}]", request.getParameterMap());
        logger.info("map: [{}]", map);
        if (logger.isInfoEnabled()) {
            logger.info("request stream: [{}]",
                    StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8));
        }

        Map<String, String> response = new HashMap<>();

        response.put("echo", "true");

        response.putAll(map);

        return response;
    }
}
