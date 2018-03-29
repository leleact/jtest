package com.lele.test.dubbo.reference;

import com.alibaba.dubbo.rpc.service.GenericService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(value = {"classpath:dubbo/dubbo-demo-consumer.xml"})
public class TestDubboReferenceTests {

    @Autowired
    private GenericService demoService;

    @Test
    public void consumTest() {
        demoService.$invoke("xxx", new String[]{"Map"}, new Object[]{"xxx"});
    }

}
