package com.github.leleact.jtest.dubbo.starter;

import com.github.leleact.jtest.dubbo.starter.dubbo.model.HasMapModel;
import com.github.leleact.jtest.dubbo.starter.dubbo.service.MapTransisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest
public class TestDubboStarterApplicationTests {

    @DubboReference
    private MapTransisService mapTransisService;

    //@Test
    @Disabled
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
