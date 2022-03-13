package com.github.leleact.jtest.dubbo.starter;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.leleact.jtest.dubbo.starter.dubbo.model.HasMapModel;
import com.github.leleact.jtest.dubbo.starter.dubbo.service.MapTransisService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class TestDubboStarterApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(TestDubboStarterApplicationTests.class);

    @Reference
    private MapTransisService mapTransisService;

    @Test
    public void mapTransisTest() {
        final String name = "lele";
        final Integer age = 450000;
        HasMapModel mapModel = new HasMapModel();
        mapModel.setName(name);
        mapModel.setAge(age);
        Map<String, String> mm = new HashMap<>();
        mm.put("name", name);
        mapModel.setMm(mm);

        HasMapModel resp = mapTransisService.transis(mapModel);

        Assertions.assertEquals(name, resp.getName());
        Assertions.assertEquals(age, resp.getAge());
        Map<String, String> remm = resp.getMm();
        Assertions.assertNotNull(remm);
        Assertions.assertEquals(name, remm.get("name"));
    }

}
