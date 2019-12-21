package com.github.leleact.jtest.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAliasType;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

@Slf4j
class XStreamTests {

    @Test
    public void serializeTest() {

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
        Assertions.assertNotNull(out);
    }

    @Data
    @XStreamAliasType("message")
    public static class Message {
        @XStreamAlias("a")
        private A inner;
    }

    @Data
    @XStreamAliasType("a")
    public static class A {
        @XStreamAsAttribute
        protected String id;
    }

    @Setter
    @Getter
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    @XStreamAliasType("a1")
    public static class A1 extends A {
        private String name1;
        private Integer age1;
    }

    @Setter
    @Getter
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    @XStreamAliasType("a2")
    public static class A2 extends A {
        private String name2;
        private Integer age2;
    }

    @Test
    public void deserializeTest() {
        XStream xStream = new XStream();
        //xStream.autodetectAnnotations(true);
        xStream.aliasSystemAttribute(null, "class");
        xStream.aliasType("a2", A2.class);
        xStream.aliasType("a1", A2.class);
        xStream.aliasType("a", A.class);
        xStream.alias("message", Message.class);
        xStream.alias("a", A.class, A2.class);
        xStream.alias("a1", A1.class);
        xStream.alias("a2", A2.class);
        xStream.aliasField("a", Message.class, "inner");
        // deserialize key
        xStream.aliasField("a2", Message.class, "inner");
        xStream.aliasAttribute(A.class, "id", "id");
        xStream.allowTypes(new Class[]{Message.class, A.class, A1.class, A2.class});
        String xml = null;
        {
            Message message = new Message();
            A2 a2 = new A2();
            a2.setId("this is id");
            a2.setName2("leleact");
            a2.setAge2(10);
            message.setInner(a2);
            xml = xStream.toXML(message);
            log.info("xml=[{}]", xml);
            Assertions.assertNotNull(xml);
        }
        {
            xml = "<message>\n" +
                "  <a2 id=\"this is id\">\n" +
                "    <name2>leleact</name2>\n" +
                "    <age2>10</age2>\n" +
                "  </a2>\n" +
                "</message>";
            Message message = (Message) xStream.fromXML(xml);
            log.info("message=[{}]", message);
            Assertions.assertNotNull(message);
        }

    }

}
