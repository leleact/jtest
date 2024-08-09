package com.github.leleact.jtest.mybatis.spring.application;

import com.github.leleact.jtest.mybatis.spring.service.JsonTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * MySQL json Type application.
 *
 * @author leleact
 * @since 2024-08-06
 */
@Slf4j
public class MySQLJsonTypeApp {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            "classpath:spring/spring-long-trx.xml");
        try (context) {
            JsonTypeService service = context.getBean(JsonTypeService.class);
            service.executor();
        }
    }
}
