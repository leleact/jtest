package com.lele.test.jdk.lambda;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapTests {

    private static final Logger logger = LoggerFactory.getLogger(MapTests.class);

    class Pojo {
        private String id;

        private String name;

        private Integer age;

        public Pojo(String id, String name, Integer age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public String getId() {
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
    public void streamMapTest() {

        List<Pojo> list = Arrays.asList(new Pojo("1", "a", 10), new Pojo("2", "b", 11), new Pojo("3", "c", 12));

        List<String> r = list.stream().map(Pojo::getId).collect(Collectors.toList());

        logger.info("r:{}", r);
    }
}
