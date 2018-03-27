package com.lele.test.testspringbootpropertysource.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigure {

    @Bean
    public Bean2 bean2() {
        Bean2 b2 = new Bean2();
        b2.setS2("bb");
        b2.setBean1(bean1());
        return b2;
    }

    @Bean
    public Bean1 bean1() {
        Bean1 b1 =  new Bean1();
        b1.setS1("aa");
        return b1;
    }
}
