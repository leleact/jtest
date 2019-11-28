package com.github.leleact.jtest.dubbo.generic;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest
public class DubboGenericApplicationTests {

    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void invokeGenericTest() {
        ApplicationConfig applicationConfig = applicationContext.getBean(ApplicationConfig.class);
        RegistryConfig registryConfig = applicationContext.getBean(RegistryConfig.class);
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>(); // 此实例很重，封装了与注册中⼼的连接以及与提供者的连接，请⾃⾏缓存，否则可能造成内存和连接泄漏
        reference.setApplication(applicationConfig);
        reference.setRegistry(registryConfig); // 多个注册中⼼可以⽤setRegistries()
        reference.setInterface("com.github.leleact.jtest.dubbo.provider.dubbo.service.GenericInvokeService");
        reference.setGeneric(true);

        GenericService genericService = reference.get();

        Map<String, String> param = new HashMap<>();
        param.put("name", "a");
        param.put("age", "111");

        Map<String, String> object = (Map<String, String>) genericService.$invoke("invoke",
                                                                                  new String[]{"com.github.leleact.jtest.dubbo.provider.bean.request.GenericRequest"},
                                                                                  new Object[]{param});
        log.info("result: [{}]", object.toString());
    }
}
