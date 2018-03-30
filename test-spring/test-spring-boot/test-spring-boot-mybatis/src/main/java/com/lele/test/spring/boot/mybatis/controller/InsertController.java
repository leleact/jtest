package com.lele.test.spring.boot.mybatis.controller;

import com.lele.test.spring.boot.mybatis.service.BatchInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InsertController {

    @Autowired
    private BatchInsertService batchInsertService;

    @GetMapping(value = "/post")
    public String insertBatch(Integer record) {
        batchInsertService.batchInsert(record);
        return "ok";
    }
}
