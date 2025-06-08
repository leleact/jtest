package com.github.leleact.jtest.spring.framework.ioc.bean.post;

import lombok.Getter;
import lombok.Setter;

/**
 * a.
 *
 * @author leleact
 * @since 2025-06-07
 */
@Getter
@Setter
public class MyBean1 {

    private MyBean myBean;

    public MyBean1(MyBean myBean) {
        this.myBean = myBean;
    }
}
