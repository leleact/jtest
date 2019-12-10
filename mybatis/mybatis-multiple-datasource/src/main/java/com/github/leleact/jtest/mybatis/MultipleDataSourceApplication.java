package com.github.leleact.jtest.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// DataSourceAutoConfiguration Must exclude???
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MultipleDataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDataSourceApplication.class, args);
    }
}
