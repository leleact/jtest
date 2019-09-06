package com.github.leleact.jtest.jdk.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
class StreamTests {

    @Data
    @AllArgsConstructor
    class Item implements Comparable<Item> {

        private String str1;

        private String str2;

        private Long amt;

        @Override
        public int compareTo(Item o) {
            int code = this.str1.compareTo(o.str1);
            if (code != 0) {
                return code;
            }

            code = this.str2.compareTo(o.str2);
            if (code != 0) {
                return code;
            }
            code = this.amt.compareTo(o.amt);
            return code;
        }
    }

    @Data
    class Key implements Comparable<Key> {
        private String str1;

        private String str2;

        @Override
        public int compareTo(Key o) {
            int code = this.str1.compareTo(o.str1);
            if (code != 0) {
                return code;
            }
            return this.str2.compareTo(o.str2);
        }
    }

    Key from(Item item) {
        Key key = new Key();
        key.str1 = item.str1;
        key.str2 = item.str2;
        return key;
    }

    @Test
    void groupByTest() {
        List<Item> list = new ArrayList<>();
        list.add(new Item("a", "d", 99L));
        list.add(new Item("a", "b", 100L));
        list.add(new Item("a", "b", 101L));
        list.add(new Item("a", "c", 101L));
        list.add(new Item("a", "a", 100L));

        list.stream().sorted().collect(Collectors.groupingBy(this::from)).forEach(
            (k, it) -> {
                final Item item = new Item(k.str1, k.str2, 0L);
                it.forEach(i -> item.amt += i.amt);
                log.info("key=[{}], value=[{}]", k, item);
            });
    }
}
