package com.github.leleact.jtest.spring.boot.uploadfile.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
public class FileUploadController {

    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @PostMapping("/upload/gateway")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            byte[] fileBytes = FileCopyUtils.copyToByteArray(file.getInputStream());
            String base64file = Base64.getEncoder().encodeToString(fileBytes);
            log.info("file {}, content {}", file.getName(), base64file);
        } catch (IOException e) {
            log.error("read file error", e);
            return "fail";
        }
        return "ok";
    }
}
