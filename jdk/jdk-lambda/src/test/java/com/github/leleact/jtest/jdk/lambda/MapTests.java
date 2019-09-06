package com.github.leleact.jtest.jdk.lambda;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
class MapTests {

    class Pojo {
        private String id;

        private String name;

        private Integer age;

        Pojo(String id, String name, Integer age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

    @Test
    void streamMapTest() {

        List<Pojo> list = Arrays.asList(new Pojo("1", "a", 10), new Pojo("2", "b", 11), new Pojo("3", "c", 12));

        List<String> r = list.stream().map(Pojo::getId).collect(Collectors.toList());

        log.info("r:{}", r);
    }
}
