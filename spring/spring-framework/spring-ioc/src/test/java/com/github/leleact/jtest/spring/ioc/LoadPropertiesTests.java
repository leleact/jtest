package com.github.leleact.jtest.spring.ioc;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class LoadPropertiesTests {

    @Test
    void loadPropertiesTest() throws IOException {
        String path = "META-DATA/loadtest.properties";
        LoadProperties loadProperties = new LoadProperties(path);

        Assertions.assertEquals("1", loadProperties.getProperties("a"));
        Assertions.assertEquals("2", loadProperties.getProperties("b"));
    }

}
