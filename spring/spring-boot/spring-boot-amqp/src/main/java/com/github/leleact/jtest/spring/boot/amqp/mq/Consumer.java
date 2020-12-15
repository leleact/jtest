package com.github.leleact.jtest.spring.boot.amqp.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class Consumer {

    @RabbitListener(
        queuesToDeclare = {
            @Queue(name = "test_queue", durable = "false", autoDelete = "true")
        })
    public void receive(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag,
                        @Header(AmqpHeaders.CONSUMER_QUEUE) String queue) throws IOException {

        log.info("receive message:{}, tag:{}, queue:{}", message, tag, queue);
        try {
            Thread.sleep(50000L);
        } catch (InterruptedException e) {
            channel.basicAck(tag, false);
        }
        channel.basicAck(tag, true);
        log.info("message {}, tag: {} consume done", message, tag);
    }


    @RabbitListener(
        queuesToDeclare = {
            @Queue(name = "test_create_queue",
                   durable = "false",
                   autoDelete = "true",
                   arguments = {@Argument(name = "x-message-ttl", value = "60000", type = "java.lang.Long")})
        })
    public void createAndReceive(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag,
                                 @Header(AmqpHeaders.CONSUMER_QUEUE) String queue) throws IOException {

        log.info("receive message:{}, tag:{}, queue:{}", message, tag, queue);

        channel.basicAck(tag, true);
    }
}
