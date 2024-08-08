package com.github.leleact.jtest.jdk.stream;

import lombok.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * jdk stream api test
 *
 * @author leleact
 * @since 2024-07-30
 */
public class ListToMapTests {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Key implements Comparable<Key> {
        private String k1;
        private String k2;
        private String k3;

        @Override
        public int compareTo(Key o) {
            return 0;
        }
    }

    @Getter
    @Setter
    public static class Pojo extends Key {
        private String p1;
        private String p2;
    }

    @Test
    public void listToTreeMapTest() {
        List<Pojo> dataList = new ArrayList<>();
        {
            Pojo p = new Pojo();
            p.setK1("p1");
            p.setK2("p2");
            p.setK3("p3");
            dataList.add(p);
        }
        {
            Pojo p = new Pojo();
            p.setK1("p1");
            p.setK2("p2");
            p.setK3("p4");
            dataList.add(p);
        }

        Map<Key, List<Pojo>> map = dataList.stream()
                                           .collect(Collectors.groupingBy(d -> new Key(d.getK1(), d.getK2(), d.getK3()),
                                               TreeMap::new,
                                               Collectors.toList()));
        Assertions.assertEquals(1, map.size());
    }
}
