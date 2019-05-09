package com.github.leleact.test.spring.mvc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesUtil {

    public static Properties readPropertiesWithClass(String name) throws IOException {
        InputStream inputStream = ReadPropertiesUtil.class.getResourceAsStream(name);
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }

    public static Properties readPropertiesWithClassLoader(String name) throws IOException {
        //ReadPropertiesUtil.class.getClassLoader()是ParalletWebappClassLoader
        //获取resource的时候获取的路径是/WEB-INF/classes/name
        InputStream inputStream = ReadPropertiesUtil.class.getClassLoader().getResourceAsStream(name);
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }

}
