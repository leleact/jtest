package com.lele.test.jdk.lambda;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StreamTests {

    private static final Logger logger = LoggerFactory.getLogger(StreamTests.class);

    class Item implements Comparable<Item> {

        private String str1;

        private String str2;

        private Long amt;

        public Item(String str1, String str2, Long amt) {
            this.str1 = str1;
            this.str2 = str2;
            this.amt = amt;
        }

        public String getStr1() {
            return str1;
        }

        public void setStr1(String str1) {
            this.str1 = str1;
        }

        public String getStr2() {
            return str2;
        }

        public void setStr2(String str2) {
            this.str2 = str2;
        }

        public Long getAmt() {
            return amt;
        }

        public void setAmt(Long amt) {
            this.amt = amt;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Item))
                return false;
            Item item = (Item) o;
            return Objects.equals(str1, item.str1) &&
                    Objects.equals(str2, item.str2) &&
                    Objects.equals(amt, item.amt);
        }

        @Override
        public int hashCode() {
            return Objects.hash(str1, str2, amt);
        }

        @Override
        public String toString() {
            return "A{" +
                    "str1='" + str1 + '\'' +
                    ", str2='" + str2 + '\'' +
                    ", amt=" + amt +
                    '}';
        }

        // TODO NullPointerException
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

    class Key implements Comparable<Key> {
        private String str1;

        private String str2;

        public String getStr1() {
            return str1;
        }

        public void setStr1(String str1) {
            this.str1 = str1;
        }

        public String getStr2() {
            return str2;
        }

        public void setStr2(String str2) {
            this.str2 = str2;
        }

        @Override
        public int compareTo(Key o) {
            int code = this.str1.compareTo(o.str1);
            if (code != 0) {
                return code;
            }
            return this.str2.compareTo(o.str2);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Key))
                return false;
            Key key = (Key) o;
            return Objects.equals(str1, key.str1) &&
                    Objects.equals(str2, key.str2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(str1, str2);
        }

        @Override
        public String toString() {
            return "Key{" +
                    "str1='" + str1 + '\'' +
                    ", str2='" + str2 + '\'' +
                    '}';
        }
    }

    Key from(Item item) {
        Key key = new Key();
        key.str1 = item.str1;
        key.str2 = item.str2;
        return key;
    }

    @Test
    public void groupByTest() {
        List<Item> list = new ArrayList<>();
        list.add(new Item("a", "d", 99L));
        list.add(new Item("a", "b", 100L));
        list.add(new Item("a", "b", 101L));
        list.add(new Item("a", "c", 101L));
        list.add(new Item("a", "a", 100L));

        list.stream().sorted().collect(Collectors.groupingBy(this::from)).forEach(
                (k, it) -> {
                    final Item item = new Item(k.str1, k.str2, 0L);
                    it.forEach(i-> item.amt += i.amt);
                 logger.info("key=[{}], value=[{}]", k, item);
                });
    }
}
