package com.lele.test.spring.oxm;

import com.lele.test.spring.oxm.bean.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RunWith(SpringRunner.class)
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
