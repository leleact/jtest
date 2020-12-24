package com.github.leleact.jtest.mybatis.page.helper;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackageClasses = {com.github.leleact.jtest.mybatis.page.helper.mapper.T1Mapper.class},
            annotationClass = Mapper.class)
public class MybatisPageHelperApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPageHelperApplication.class, args);
    }
}
