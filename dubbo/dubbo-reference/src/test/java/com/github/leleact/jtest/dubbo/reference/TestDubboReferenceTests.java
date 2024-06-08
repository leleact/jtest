package com.github.leleact.jtest.dubbo.reference;

import org.apache.dubbo.rpc.service.GenericService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ContextConfiguration(value = {"classpath:dubbo/dubbo-demo-consumer.xml"})
public class TestDubboReferenceTests {

    @Autowired
    private GenericService demoService;

    @Disabled
    @Test
    @Repeat(10)
    public void consumTest() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Map<String, String> map = new HashMap<>();
        map.put("id", sdf.format(new Date()));
        map.put("exchange", "haha");
        demoService.$invoke("saveAndSend", new String[]{"com.lele.test.dubbo.reference.GenericService"},
            new Object[]{map});
    }
}
