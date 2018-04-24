package com.lele.test.spring.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {

    private static final Logger log = LoggerFactory.getLogger(LoadProperties.class);

    private Properties properties;

    public LoadProperties(String path) throws IOException {
        InputStream inputStream = LoadProperties.class.getClassLoader().getResourceAsStream(path);
        //InputStream inputStream = this.getClass().getResourceAsStream(path);
        properties = new Properties();
        properties.load(inputStream);
    }

    public String getProperties(String key) {
        return this.properties.getProperty(key);
    }
}
