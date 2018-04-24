package com.lele.test.spring.mvc.controller;

import com.lele.test.spring.mvc.utils.ReadPropertiesUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping("/prop")
public class PropertiesController {

    @GetMapping("/class")
    Map<String, String> getPropWithClass() throws IOException {
        Properties properties = ReadPropertiesUtil.readPropertiesWithClass("/config/config.properties");
        Map<String, String> map = new HashMap<>();
        Enumeration<?> enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement().toString();
            map.put(key, properties.getProperty(key));
        }
        return map;
    }

    @GetMapping("/classloader")
    Map<String, String> getPropWithClassLoader() throws IOException {
        Properties properties = ReadPropertiesUtil.readPropertiesWithClassLoader("/config/config.properties");
        Map<String, String> map = new HashMap<>();
        Enumeration<?> enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement().toString();
            map.put(key, properties.getProperty(key));
        }
        return map;
    }
}
