package com.github.leleact.jtest.lombok;

import lombok.*;
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


    @Setter
    @Getter
    public static class Base1 {
        protected String name;
    }

    @Builder(builderMethodName = "hiddenBuilder", access = AccessLevel.PROTECTED)
    public static class Pojo2 extends Base1 {
        private Integer age;
        private String address;

        public static Pojo2Builder builder(String name) {
            // can't build parent field
            // return hiddenBuilder().name(name);
            return hiddenBuilder();
        }
    }

    @Test
    public void protectedFieldBuilderTest() {
        Pojo2 pojo = Pojo2.builder("a").age(1).address("ABC").build();
        Assertions.assertNull(pojo.name);
        Assertions.assertEquals(1, pojo.age);
        Assertions.assertEquals("ABC", pojo.address);
    }
}
