package com.github.leleact.jtest.mybatis.spring.application;

import com.github.leleact.jtest.mybatis.spring.service.LongTrxSequenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SpringLongTxApp {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-long-trx.xml");
        LongTrxSequenceService service = context.getBean(LongTrxSequenceService.class);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>());

        int nTask = 1;
        for (int i = 0; i < nTask; i++) {
            threadPoolExecutor.execute(() -> service.syncSequence("a", 1));
        }
        log.info("aaa");
        threadPoolExecutor.awaitTermination(1, TimeUnit.MINUTES);
        log.info("bbb");
        ((ConfigurableApplicationContext) context).close();
    }
}
