package com.lele.test.spring.boot.amqp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSpringBootAmqpApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Exchange exchange;

	@Test
	public void putMessageTest() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        int total = 1000000;
        int count = 0;
        while (count++ < total) {
            String message = "hello @" + sdf.format(new Date());
            CorrelationData correlationData = new CorrelationData(sdf.format(new Date()));
            rabbitTemplate.convertAndSend(exchange.getName(), "xx", message, correlationData);
        }
    }

}
