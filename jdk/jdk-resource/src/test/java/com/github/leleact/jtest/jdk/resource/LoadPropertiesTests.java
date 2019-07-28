package com.github.leleact.jtest.jdk.resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class LoadPropertiesTests {

    @Test
    void loadPropertiesWithClassAbsolutePath() throws IOException {
        // 绝对路径
        InputStream inputStream = this.getClass().getResourceAsStream("/config.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        Assertions.assertEquals("1", properties.getProperty("a"));
        Assertions.assertEquals("2", properties.getProperty("b"));
    }

    @Test
    void loadPropertiesWithClassRelativePath() throws IOException {
        // 相对路径
        InputStream inputStream = this.getClass().getResourceAsStream("../../../../../config.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        Assertions.assertEquals("1", properties.getProperty("a"));
        Assertions.assertEquals("2", properties.getProperty("b"));
    }

    @Test
    void loadPropertiesWithClassLoader() throws IOException {
        // 只有绝对路径
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        Assertions.assertEquals("1", properties.getProperty("a"));
        Assertions.assertEquals("2", properties.getProperty("b"));
    }
}
