package com.github.leleact.jtest.spring.tx.config;

import com.github.leleact.jtest.spring.tx.util.TransactionUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionConfiguration {

    @Bean
    public TransactionUtil transactionUtil() {
        return new TransactionUtil();
    }
}
