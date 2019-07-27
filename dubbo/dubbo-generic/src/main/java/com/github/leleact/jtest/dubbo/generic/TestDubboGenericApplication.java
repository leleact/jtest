package com.github.leleact.jtest.dubbo.generic;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class TestDubboGenericApplication {

    private static final Logger log = LoggerFactory.getLogger(TestDubboGenericApplication.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(TestDubboGenericApplication.class, args);
        ApplicationConfig applicationConfig = applicationContext.getBean(ApplicationConfig.class);
        RegistryConfig registryConfig = applicationContext.getBean(RegistryConfig.class);
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>(); // 此实例很重，封装了与注册中⼼的连接以及与提供者的连接，请⾃⾏缓存，否则可能造成内存和连接泄漏
        reference.setApplication(applicationConfig);
        reference.setRegistry(registryConfig); // 多个注册中⼼可以⽤setRegistries()
        reference.setInterface("com.lele.test.dubbo.provider.dubbo.service.GenericInvokeService");
        reference.setGeneric(true);

        GenericService genericService = reference.get();

        Map<String, String> object = (Map<String, String>) genericService.$invoke("invoke", new String[]{"com.lele.test.dubbo.provider.bean.request.GenericRequest"}, new Object[]{
                new HashMap<String, String>() {
                    {
                        put("name", "a");
                        put("age", "111");
                    }
                }
        });
        log.info("{}", object.toString());
    }

}
