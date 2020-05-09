package com.github.leleact.jtest.fastjson;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class JSONBeanTest {

    private static Logger log = LoggerFactory.getLogger(JSONBeanTest.class);

    @Test
    public void nestBeanHasMapTest() {
        Bean bean = new Bean();
        bean.setField1("aa");
        bean.setField2(1);

        Map<String, Payment> paymentMap = new HashMap<>();
        paymentMap.put("p1", new Payment("cat1", 0.3f));
        paymentMap.put("p2", new Payment("cat2", 0.5f));
        bean.setPayment(paymentMap);

        String ss = JSONObject.toJSONString(bean);
        log.info(ss);

        Bean bean1 = JSONObject.parseObject(ss, Bean.class);

        log.info(bean1.getPayment().get("p1").getCategory());
    }
}
