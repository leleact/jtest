package com.github.leleact.jtest.spring.framework.ioc.bean.post;

import lombok.Getter;
import lombok.Setter;

/**
 * bean.
 *
 * @author leleact
 * @since 2025-06-07
 */
@Getter
@Setter
public class MyBean {

    private MyBean1 myBean1;

    public MyBean(MyBean1 myBean1) {
        this.myBean1 = myBean1;
    }
}
