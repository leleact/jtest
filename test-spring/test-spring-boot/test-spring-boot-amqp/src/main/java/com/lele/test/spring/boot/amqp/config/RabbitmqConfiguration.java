package com.lele.test.spring.boot.amqp.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfiguration {

    @Bean
    public Queue queue() {
        return QueueBuilder.durable("test_queue").build();
    }

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.topicExchange("topic_exchange").build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("*").noargs();
    }
}
