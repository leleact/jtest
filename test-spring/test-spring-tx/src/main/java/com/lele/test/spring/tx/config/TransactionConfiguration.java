package com.lele.test.spring.tx.config;

import com.lele.test.spring.tx.util.TransactionUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionConfiguration {

    @Bean
    public TransactionUtil transactionUtil() {
        return new TransactionUtil();
    }
}
