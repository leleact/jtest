package com.github.leleact.jtest.lombok;

import lombok.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * lombok generic builder
 *
 * @author leleact
 * @since 2023-03-05
 */
public class LombokGenericBuilderTests {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PojoBean {
        private String name;
        private Integer age;
    }

    @Test
    public void genericBuilderTest() {
        PojoBean pojo = new PojoBean("abc", 0);
        GenericBuilderType<PojoBean> builderType = GenericBuilderType.<PojoBean>builder().clazz(PojoBean.class).instance(pojo).build();
        Assertions.assertEquals(pojo, builderType.getInstance());
    }
}
