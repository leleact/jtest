package com.github.leleact.jtest.spring.boot.mybatis.service;

import com.github.leleact.jtest.spring.boot.mybatis.bean.dto.T1;
import com.github.leleact.jtest.spring.boot.mybatis.bean.mapper.T1Mapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TransactionService {

    @Resource
    private T1Mapper t1Mapper;

    @Transactional
    public int insert(T1 t1) {
        return t1Mapper.insertSelective(t1);
    }

    /**
     * 外部调用insertWrapper时, insertWrapper内部调用的insert上的 @Transactional不生效
     * <p></p>
     * 如果加上@Transactional,外部调用时,@Transactional生效,但是 insert(t1)为什么不是调用的代理类方法,
     * 而是被代理的类??? 对比<code>@Configuration</code>下面的@Bean注解的方法, 如果一个bean依赖另外一个bean
     * <pre>{@code
     *    @literal @Configuration
     *     public class ConfigurationBean {
     *        @literal @Bean
     *         public Bean1 bean1() {
     *            return new Bean1();
     *         }
     *
     *        @literal @Bean
     *         public Bean2 bean2() {
     *             Bean2 b2 = new Bean2();
     *             b2.setBean1(bean1());
     *             return b2;
     *         }
     *     };
     * }</pre>
     * <code>b2.setBean1(bean1())</code>中<coode>bean1()</coode>调用的是代理类方法
     */
    // @Transactional(propagation= Propagation.REQUIRES_NEW)
    public int insertWrapper(T1 t1) {
        return insert(t1);
    }
}
