package com.github.leleact.jtest.spring.boot.context;

import com.github.leleact.jtest.spring.boot.context.bean.EchoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootContextApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootContextApplication.class,
                                                                                  args);
        EchoService echoService = applicationContext.getBean(EchoService.class);
        echoService.echo("a");
    }
}
