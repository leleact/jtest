package com.lele.test.spring.boot.amqp.mq;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(
            queuesToDeclare = {
                    @Queue(name = "test_queue", durable = "false", autoDelete = "true")
            })
    public void recive(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag,
            @Header(AmqpHeaders.CONSUMER_QUEUE) String queue) throws IOException {

        log.info("recive message:{}, tag:{}, queue:{}", message, tag, queue);

        channel.basicAck(tag, true);
    }


    @RabbitListener(
            queuesToDeclare = {
                    @Queue(name = "test_create_queue", durable = "false", autoDelete = "true", arguments = {@Argument(name = "x-message-ttl", value = "60000", type = "java.lang.Long")})
            })
    public void createAndRecive(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag,
            @Header(AmqpHeaders.CONSUMER_QUEUE) String queue) throws IOException {

        log.info("recive message:{}, tag:{}, queue:{}", message, tag, queue);

        channel.basicAck(tag, true);
    }
}
