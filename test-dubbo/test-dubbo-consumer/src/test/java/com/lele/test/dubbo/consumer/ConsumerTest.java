package com.lele.test.dubbo.consumer;

import com.lele.test.dubbo.api.bean.request.CommonRequest;
import com.lele.test.dubbo.api.bean.response.CommonResponse;
import com.lele.test.dubbo.api.service.HelloService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class ConsumerTest {

    private static final Logger log = LoggerFactory.getLogger(ConsumerTest.class);

    @Test
    public void startConsumer() {
        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring/dubbo-demo-consumer.xml"});
        context.start();
        HelloService demoService = (HelloService) context.getBean("demoService"); // get remote service proxy

        try {
            Thread.sleep(1000);
            CommonRequest request = new CommonRequest();
            request.setB(true);
            request.setD(10.3434);
            request.setI(1);
            request.setL(10L);
            Map<String, String> m = new HashMap<>();
            m.put("AA", "BB");
            request.setM(m);
            request.setS("" + System.currentTimeMillis());
            CommonResponse response = demoService.say1(request); // call remote method
            Map<String, String> rm = response.getM();
            for (Map.Entry<String, String> entry : rm.entrySet()) {
                log.debug(entry.getKey() + "='" + entry.getValue() + "'");
            }
            log.debug(response.toString()); // get result
        } catch (Throwable throwable) {
            log.error("", throwable);
        }
    }
}
