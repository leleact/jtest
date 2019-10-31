package com.github.leleact.jtest.spring.boot.amqp.controller;

import com.github.leleact.jtest.spring.boot.amqp.mq.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PutManyOfMessageController {

    private Producer producer;

    @GetMapping("/put")
    public String putMessages(String message, Integer count) {
        producer.produceMessage(message, count);
        return "ok";
    }

    @Autowired
    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}
