package com.lele.test.json;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lele.test.json.bean.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static com.alibaba.fastjson.serializer.SerializerFeature.*;

public class FastJsonTest {

    private Logger log = LoggerFactory.getLogger(FastJsonTest.class);

    @Test
    public void pojoTest() {
        Order order = new Order();
        order.setField1("1");
        order.setField2("2");
        order.setField3("3");

        String str = JSON.toJSONString(order);

        log.debug(str);
    }


    @Test
    public void listTest() {
        Group group = new Group();

        User user1 = new User();
        user1.setId("U1");
        user1.setName("UN1");

        User user2 = new User();
        user2.setId("U2");
        user2.setName("UN2");

        group.setUserList(new LinkedList<>());

        group.getUserList().add(user1);
        group.getUserList().add(user2);

        group.setId("G1");
        group.setGroupName("GN1");

        String str = JSON.toJSONString(group);

        log.debug(str);
    }

    @Test
    public void muiltyObjTest() {
        MuiltiObject mob = new MuiltiObject();

        mob.setId("M1");
        mob.setName("NAME1");

        Group group = new Group();

        User user1 = new User();
        user1.setId("U1");
        user1.setName("UN1");

        User user2 = new User();
        user2.setId("U2");
        user2.setName("UN2");

        group.setUserList(new LinkedList<>());

        group.getUserList().add(user1);
        group.getUserList().add(user2);

        group.setId("G1");
        group.setGroupName("GN1");

        mob.setGroup(group);

        Order order = new Order();
        order.setField1("1");
        order.setField2("2");
        order.setField3("3");

        mob.setOrder(order);

        String str = JSON.toJSONString(mob);
        log.debug(str);
    }

    @Test
    public void parseIntoInnerClass() {
        String str = "{filed1:\"aa\", filed2:\"bb\"}";

        HasStaticInnerClass.InnerClass innerClass = JSONObject.parseObject(str, HasStaticInnerClass.InnerClass.class);

        log.debug("filed1:[" + innerClass.getFiled1() + "]");
        log.debug("filed2:[" + innerClass.getFiled2() + "]");
    }

    @Test
    public void instanceInnerClass() {

        HasInnerClass hic = new HasInnerClass();

        HasInnerClass.InnerClass ic = hic.new InnerClass();

        ic.setFiled1("aa");
        ic.setFiled2("bb");

        log.debug(JSONObject.toJSONString(ic));
    }

    @Test
    public void orderField() {

        Order order = new Order();
        order.setField1("11");
        order.setField2("22");
        order.setField3("33");
        order.setAb("ab");
        order.setBb("bb");
        order.setA("a");
        log.debug(JSONObject.toJSONString(order));
    }

    @Test
    public void personTest() {
        String str = "{name:\"咳咳\"}";
        Person person = JSONObject.parseObject(str, Person.class);
        log.debug("" + person.getAge());
    }

    @Test
    public void beanHasJSONObj() {

        PersonHasJsonObj p = new PersonHasJsonObj();

        p.setName("333");
        p.setAge(12);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aa", "11");
        jsonObject.put("bb", "22");
        p.setJsonObject(jsonObject);

        log.debug(JSONObject.toJSONString(p));
    }

    @Test
    public void escapeTest() {
        String url = "https://mixpayuat1.orangebank.com.cn/?f=9150787232258644286000660&O=17a3dd718db492db4f0c3c5ad254942b";

        Map<String, String> map = new HashMap<>();

        map.put("mixPayUrl", url);

        log.debug(JSONObject.toJSONString(map));
    }

    @Test
    public void parseComplexObject() {

        B b = new B();
        b.setbName("bname");

        A a = new A();
        a.setaName("aname");

        b.setA(a);

        String str = JSONObject.toJSONString(b);
        log.info(str);

        B b1 = JSONObject.parseObject(str, B.class);

        log.info(b1.getbName() + ", " + b1.getA().getaName());
    }


    @Test
    public void mixpayTest() {

        TradeResult tradeResult = new TradeResult();
        tradeResult.setReturn_code("SUCCESS");
        tradeResult.setReturn_msg("OK");
        tradeResult.setAppid("wxa8d0b58cefc18eb2");
        tradeResult.setMch_id("1900008721");
        tradeResult.setSub_mch_id("30230438");
        tradeResult.setNonce_str("6CsXOPU0w9kYPBzB");
        tradeResult.setSign("72E1B14E5E82AB7ADA999329397E3CD4");
        tradeResult.setResult_code("SUCCESS");
        tradeResult.setPrepay_id("wx20171013193158a05cd6505c0614692279");
        tradeResult.setTrade_type("NATIVE");
        tradeResult.setCode_url("weixin:\\/\\/wxpay\\/bizpayurl?pr=F3kPLVy");

        MixPayOrderResponse mixPayOrderResponse = new MixPayOrderResponse();
        mixPayOrderResponse.setOrd_no("9150789431772103143710073");
        mixPayOrderResponse.setOrd_mct_id("3063");
        mixPayOrderResponse.setOrd_shop_id("3063");
        mixPayOrderResponse.setOrd_currency("CNY");
        mixPayOrderResponse.setCurrency_sign("\u00a5");
        mixPayOrderResponse.setPmt_tag("WeixinBERL");
        mixPayOrderResponse.setPmt_name("\u5fae\u4fe1\u652f\u4ed8(\u6d4b\u8bd5)");
        mixPayOrderResponse.setTrade_no("wx20171013193158a05cd6505c0614692279");
        mixPayOrderResponse.setTrade_amount("1");
        mixPayOrderResponse.setTrade_qrcode("weixin:\\/\\/wxpay\\/bizpayurl?pr=F3kPLVy");
        mixPayOrderResponse.setTrade_discout_amount("0");
        mixPayOrderResponse.setStatus("2");
        mixPayOrderResponse.setOut_no("31221530189693092323958375173947");
        mixPayOrderResponse.setTrade_result(tradeResult);

        log.info(JSONObject.toJSONString(mixPayOrderResponse, WriteMapNullValue, WriteSlashAsSpecial,
                BrowserCompatible));

    }
}
