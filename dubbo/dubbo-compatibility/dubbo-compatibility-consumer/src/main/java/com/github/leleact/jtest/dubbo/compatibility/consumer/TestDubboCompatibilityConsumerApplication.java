package com.github.leleact.jtest.dubbo.compatibility.consumer;

import com.lele.test.dubbo.compatibility.api.EchoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:META-INF/spring/dubbo-consumer.xml"})
public class TestDubboCompatibilityConsumerApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(TestDubboCompatibilityConsumerApplication.class, args);
        EchoService demoService = (EchoService) applicationContext.getBean(
                "demoService"); // get remote service proxy
        demoService.echo("11111111111111111"); // call remote method
    }

}
