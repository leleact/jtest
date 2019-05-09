package com.lele.test.spring.ioc;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class LoadPropertiesTests {

    @Test
    public void loadPropertiesTest() throws IOException {

        String path = "META-DATA/loadtest.properties";

        LoadProperties loadProperties = new LoadProperties(path);

        Assert.assertEquals("1", loadProperties.getProperties("a"));
        Assert.assertEquals("2", loadProperties.getProperties("b"));
    }

}
