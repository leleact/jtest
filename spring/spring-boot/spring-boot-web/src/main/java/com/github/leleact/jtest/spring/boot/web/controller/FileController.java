package com.github.leleact.jtest.spring.boot.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public String upload(HttpServletRequest request, @RequestPart("data") MultipartFile file) {
        return "ok";
    }
}
