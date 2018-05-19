package com.lele.test.xstream;

import com.thoughtworks.xstream.XStream;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class XStreamTests {

    private static final Logger log = LoggerFactory.getLogger(XStreamTests.class);

    @Test
    public void serilize() {

        Beans beans = new Beans();
        beans.setBeans(new ArrayList<>());

        Beans.Bean b1 = new Beans.Bean();
        b1.setId("id1");
        b1.setName("name1");
        b1.setClassName("class1");
        beans.getBeans().add(b1);

        Beans.Bean b2 = new Beans.Bean();
        b2.setId("id2");
        b2.setName("name2");
        b2.setClassName("class2");
        beans.getBeans().add(b2);

        XStream xStream = new XStream();
        xStream.processAnnotations(Beans.class);
        xStream.allowTypes(new Class[]{Beans.class});
        String out = xStream.toXML(beans);
        log.info(out);
    }

}
