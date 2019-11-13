package com.github.leleact.jtest.jdk.map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

@Slf4j
class TreeMapTests {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Pojo implements Comparable<Pojo> {

        String f1;

        String f2;

        @Override
        public int compareTo(Pojo o) {
            if (0 == this.f1.compareTo(o.f1)) {
                return this.f2.compareTo(o.f2);
            } else {
                return this.f1.compareTo(o.f1);
            }
        }
    }

    @Test
    void firstEntryTest() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(2, "2");
        map.put(1, "1");
        map.put(3, "3");
        Assertions.assertEquals("1", map.firstEntry().getValue());

        map.put(-1, "-1");
        Assertions.assertEquals("-1", map.firstEntry().getValue());
    }

    @Test
    void putTest() {
        TreeMap<Pojo, Integer> map = new TreeMap<>();
        Pojo p1 = new Pojo("a", "b");
        map.put(p1, 1);

        Pojo p2 = new Pojo("a", "c");
        map.put(p2, 2);

        Assertions.assertEquals(2, map.size());
    }

    @Test
    void accessTest() {
        TreeMap<Pojo, Integer> map = new TreeMap<>();
        Pojo p1 = new Pojo("i", "a");
        Pojo p2 = new Pojo("a", "a");
        Pojo p3 = new Pojo("p", "a");
        map.put(p3, 1);
        map.put(p1, 1);
        map.put(p2, 1);
        map.forEach((k, v) -> log.info("key: [{}], value: [{}]", k, v));
    }

    @Test
    public void nullKeyTest() {
        TreeMap<Pojo, String> map = new TreeMap<>();
        Pojo p1 = new Pojo("i", "a");
        Pojo p2 = new Pojo("a", "a");
        Pojo p3 = new Pojo("p", "a");
        Assertions.assertThrows(NullPointerException.class, () -> {
            String value = map.get(null);
        });
    }
}
