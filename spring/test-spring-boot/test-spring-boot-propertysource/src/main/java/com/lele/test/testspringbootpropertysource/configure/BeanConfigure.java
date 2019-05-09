package com.lele.test.testspringbootpropertysource.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigure {

    @Bean
    public Bean1 bean1() {
        Bean1 b1 = new Bean1();
        b1.setS1("aa");
        return b1;
    }

    @Bean
    public Bean2 bean2() {
        Bean2 b2 = new Bean2();
        b2.setS2("bb");
        // bean1() 方法会进入子类重写之后的bean1()方法
        // spring 会根据@Configuration注解生产BeanConfigure的子类
        b2.setBean1(bean1());
        return b2;
    }
}
