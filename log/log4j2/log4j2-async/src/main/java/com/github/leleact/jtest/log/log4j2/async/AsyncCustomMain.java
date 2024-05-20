package com.github.leleact.jtest.log.log4j2.async;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class AsyncCustomMain {

    static {
        try {
            URL url = AsyncCustomMain.class.getClassLoader().getResource("log4j2-test-async-custom.xml");
            assert url != null;
            // log4j2 XmlConfiguration监听文件需要传入url或者file对象
            Configurator.initialize(null, ConfigurationSource.fromUri(url.toURI()));
        } catch (Exception e) {
            System.out.println("Error initializing log4j2.xml");
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncCustomMain.class);
    private static final Logger LOGGER_A = LoggerFactory.getLogger(AsyncCustomMain.class.getName() + ".a");
    private static final Logger LOGGER_B = LoggerFactory.getLogger(AsyncCustomMain.class.getName() + ".b");

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 6000000; i++) {
            LOGGER_B.info("Main");
        }
        long end = System.currentTimeMillis();
        LOGGER.info("B: {} ms", end - start);
        start = System.currentTimeMillis();
        for (int i = 0; i < 6000000; i++) {
            LOGGER_A.info("Main");
        }
        end = System.currentTimeMillis();
        LOGGER.info("A: {} ms", end - start);
    }
}
