package com.github.leleact.jtest.spring.oxm;

import com.github.leleact.jtest.spring.oxm.bean.A;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.oxm.xstream.XStreamMarshaller;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
public class TestOxmApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(TestOxmApplicationTests.class);

    @Resource
    private XStreamMarshaller xStreamMarshaller;

    @Test
    public void unmarshalTest() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream("/AA.xml");

        Object o = xStreamMarshaller.unmarshalInputStream(inputStream);

        logger.info("o : [ {} ]", o);
    }

    @Test
    public void marshalTest() throws IOException {

        A a = new A();
        a.setXx("6");
        a.setYy("7");

        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();

        xStreamMarshaller.marshalOutputStream(a, arrayOutputStream);

        logger.info("o : [ {} ]", arrayOutputStream.toString());
    }
}
