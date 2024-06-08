package com.github.leleact.jtest.spring.oxm;

import com.github.leleact.jtest.spring.oxm.bean.A;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class TestOxmApplication {

    @Bean
    public XStreamMarshaller xStreamMarshaller() {
        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
        Map<String, Object> map = new HashMap<>();
        map.put("AA", A.class);
        xStreamMarshaller.setAliases(map);
        xStreamMarshaller.setTypePermissions(AnyTypePermission.ANY);
        return xStreamMarshaller;
    }

    public static void main(String[] args) {
        SpringApplication.run(TestOxmApplication.class, args);
    }
}
