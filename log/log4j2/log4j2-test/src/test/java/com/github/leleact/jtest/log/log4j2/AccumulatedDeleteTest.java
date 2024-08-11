package com.github.leleact.jtest.log.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * IfAccumulatedFileSize.
 *
 * @author leleact
 * @since 2024-08-10
 */
public class AccumulatedDeleteTest {
    private static final Logger log = LoggerFactory.getLogger(StackTraceTests.class);

    @BeforeAll
    public static void before() {
        try {
            URL url = AccumulatedDeleteTest.class.getClassLoader().getResource("log4j2-delete-accumulated.xml");
            assert url != null;
            ConfigurationSource configurationSource = ConfigurationSource.fromUri(url.toURI());
            LoggerContext context = (LoggerContext) LogManager.getContext(false);
            Configuration configuration = ConfigurationFactory.getInstance()
                                                              .getConfiguration(context, configurationSource);
            context.reconfigure(configuration);
        } catch (Exception e) {
            System.out.println("Error initializing log4j2-stack-trace.xml");
        }
    }

    @Test
    public void writeLogAndDeleteTest() {
        for (int i = 0; i < 102400; i++) {
            log.info("{}", memSetString());
        }
    }

    private StringBuilder memSetString() {
        StringBuilder sb = new StringBuilder();
        sb.append("1".repeat(10240));
        return sb;
    }
}
