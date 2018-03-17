package com.lele.test.dubbo.provider;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProvideTest {

    private static Logger log = LoggerFactory.getLogger(ProvideTest.class);

//    @Test
//    public void provide() {
//
//        HelloService helloService = new HelloServiceImpl();
//
//        ApplicationConfig applicationConfig = new ApplicationConfig();
//        applicationConfig.setName("provide-test");
//
//        RegistryConfig registryConfig = new RegistryConfig();
//        registryConfig.setAddress("zookeeper://11.8.36.50:2181");
//
//        ProtocolConfig protocol = new ProtocolConfig();
//        protocol.setName("dubbo");
//        protocol.setPort(12345);
//        protocol.setThreads(200);
//
//
//        ServiceConfig<HelloService> service = new ServiceConfig<>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
//        service.setApplication(applicationConfig);
//        service.setRegistry(registryConfig);            // 多个注册中心可以用setRegistries()
//        service.setProtocol(protocol);                  // 多个协议可以用setProtocols()
//        service.setInterface(HelloService.class);
//        service.setRef(helloService);
//        service.setVersion("1.0.0");
//
//        // 暴露及注册服务
//        service.export();
//    }

    @Test
    public void startProvider() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/dubbo-demo-provider.xml"});
        context.start();
        System.in.read(); // press any key to exit
    }
}
