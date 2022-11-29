package com.github.leleact.jtest.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * lombok builder inherit
 *
 * @author leleact
 * @since 2021-12-19
 */
@Slf4j
public class LombokBuilderInheritTests {
    @SuperBuilder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BaseObject {
        private int id;
        private String name;
    }

    @SuperBuilder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InheritObject extends BaseObject {
        private String category;
    }

    @Test
    public void baseObjectBuilderTest() {
        BaseObject bo = BaseObject.builder().id(1).name("a").build();
        Assertions.assertEquals(1, bo.id);
        Assertions.assertEquals("a", bo.name);
    }

    @Test
    public void inheritObjectBuilderTest() {
        InheritObject io = InheritObject.builder().id(0).name("a").category("a1").build();
        Assertions.assertEquals(0, io.getId());
        Assertions.assertEquals("a", io.getName());
        Assertions.assertEquals("a1", io.getCategory());
    }

    @Builder(builderMethodName = "hiddenBuilder")
    public static class Pojo1 {
        private String name;

        private Integer age;

        private String address;

        public static Pojo1Builder builder(String name) {
            return hiddenBuilder().name(name);
        }
    }

    @Test
    public void requiredArgsTest() {
        Pojo1 pojo = Pojo1.builder("a").age(1).address("ABC").build();
        Assertions.assertEquals("a", pojo.name);
        Assertions.assertEquals(1, pojo.age);
        Assertions.assertEquals("ABC", pojo.address);
    }
}
