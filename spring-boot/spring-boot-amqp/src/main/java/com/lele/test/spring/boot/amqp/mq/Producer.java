package com.lele.test.spring.boot.amqp.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class Producer {

    private static final Logger log = LoggerFactory.getLogger(Producer.class);

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Async
    public void produceMessage(String message, Integer count) {
        log.info("message:{}, count:{}", message, count);
        Integer realCount = count;
        if (realCount <= 0) {
            realCount = 1;
        }

        String realMessage = message;
        if (StringUtils.isEmpty(realMessage)) {
            realMessage = "hello";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        int i = 0;
        while (i++ < realCount) {
            String sendMessage = realMessage + " @ " + sdf.format(new Date());
            CorrelationData correlationData = new CorrelationData(sdf.format(new Date()));
            rabbitTemplate.convertAndSend("topic_exchange", "xx", sendMessage, correlationData);
        }
    }
}
