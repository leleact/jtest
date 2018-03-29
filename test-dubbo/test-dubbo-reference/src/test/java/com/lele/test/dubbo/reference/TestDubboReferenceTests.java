package com.lele.test.dubbo.reference;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@ContextConfiguration(value = {"classpath:dubbo/dubbo-demo-consumer.xml"})
public class TestDubboReferenceTests {

    @Autowired
    private GenericService demoService;

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
