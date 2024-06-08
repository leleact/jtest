package com.github.leleact.jtest.spring.cloud.feign.client;

import com.github.leleact.jtest.spring.cloud.feign.api.*;
import com.github.leleact.jtest.spring.cloud.feign.api.request.*;
import com.github.leleact.jtest.spring.cloud.feign.api.response.QueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
//@SpringBootTest
public class FeignClientApplicationTests {

    @Autowired
    private EchoServiceApi echoServiceApi;

    @Autowired
    private WaitServiceApi waitServiceApi;

    @Autowired
    private QueryServiceApi queryServiceApi;

    @Autowired
    private HierarchyServiceApi hierarchyServiceApi;

    @Autowired
    private GenericServiceApi genericServiceApi;

    //@Test
    public void echoServiceTest() {
        String res = echoServiceApi.echo("xxx");
        log.info("res: [{}]", res);
        Assertions.assertEquals("hello xxx", res);
    }

    //@Test
    public void waitServiceTest() {
        log.info("start...");
        String res = waitServiceApi.waitAtTime(80000);
        log.info("res: [{}]", res);
        Assertions.assertEquals("hello, world", res);
    }

    //@Test
    public void queryTest() {
        log.info("start...");
        QueryRequest request = new QueryRequest();
        request.setPage(1);
        request.setRows(10);
        QueryResponse<String> res = queryServiceApi.query(10L, 2, request);
        log.info("res: [{}]", res);
        Assertions.assertEquals(2, res.getEList().size());
    }

    //@Test
    public void hierarchyQeqTest() {
        HierarchyRequest request = new HierarchyRequest();
        MessageHeader header = new MessageHeader();
        request.setHeader(header);
        header.setVersion("1.0");
        String res = hierarchyServiceApi.hierarchy(request);
        Assertions.assertEquals("ok", res);
    }

    //@Test
    public void genericQeqTest() {
        GenericRequest<ComplexRequest> request = new GenericRequest<>();
        MessageHeader header = new MessageHeader();
        request.setHeader(header);
        ComplexRequest req = new ComplexRequest();
        request.setInfo(req);
        header.setVersion("1.0");

        ComplexRequest.Color color = new ComplexRequest.Color();
        color.setName("yellow");
        color.setR(162);
        color.setG(122);
        color.setB(0);
        req.setColor(color);

        ComplexRequest.Animal animal = new ComplexRequest.Animal();
        animal.setName("cat");
        animal.setLegs(4);
        animal.setAttr("Feline");
        req.setAnimal(animal);
        String res = genericServiceApi.generic(request);
        Assertions.assertEquals("ok", res);
    }
}
