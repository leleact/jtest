package com.github.leleact.jtest.log.log4j2.async;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class Main {

    static {
        try {
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("log4j2-test-async-logger.xml");
            assert inputStream != null;
            ConfigurationSource source = new ConfigurationSource(inputStream);
            Configurator.initialize(null, source);
        } catch (Exception e) {
            System.out.println("Error initializing log4j2-test-async-logger.xml");
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        LOGGER.info("xxx");
        Thread a = new Thread(new Runnable() {
            final Logger LoggerA = LoggerFactory.getLogger(Main.class.getName() + ".a");

            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    LoggerA.info("xxx");
                }
            }
        });
        Thread b = new Thread(new Runnable() {
            final Logger LoggerB = LoggerFactory.getLogger(Main.class.getName() + ".b");

            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    LoggerB.info("xxx");
                }
            }
        });

        a.start();
        b.start();

        a.join();
        b.join();
    }
}
