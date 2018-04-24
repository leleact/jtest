package com.lele.test.jdk.resource;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadPropertiesTests {

    @Test
    public void loadPropertiesWithClassAbsolutePath() throws IOException {
        // 绝对路径
        InputStream inputStream = this.getClass().getResourceAsStream("/config.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        Assert.assertEquals("1", properties.getProperty("a"));
        Assert.assertEquals("2", properties.getProperty("b"));
    }

    @Test
    public void loadPropertiesWithClassRelativePath() throws IOException {
        // 相对路径
        InputStream inputStream = this.getClass().getResourceAsStream("../../../../../config.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        Assert.assertEquals("1", properties.getProperty("a"));
        Assert.assertEquals("2", properties.getProperty("b"));
    }

    @Test
    public void loadPropertiesWithClassLoader() throws IOException {
        // 只有绝对路径
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        Assert.assertEquals("1", properties.getProperty("a"));
        Assert.assertEquals("2", properties.getProperty("b"));
    }

}
