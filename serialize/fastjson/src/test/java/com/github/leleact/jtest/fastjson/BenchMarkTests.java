package com.github.leleact.jtest.fastjson;

import com.alibaba.fastjson.JSON;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class BenchMarkTests {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    private static class Pojo {
        private String firstStr;
        private String secondStr;
        private String thirdStr;
        private String fourthStr;
        private String fifthStr;
        private Integer firstInt;
        private Integer secondInt;
        private Integer thirdInt;
        private Integer fourthInt;
        private Integer fifthInt;
    }

    private static final Integer TIMES = 100000;

    @Test
    public void fastJsonTest() {

        log.info("fast json benchmark for {} times", TIMES);
        JSON.toJSONString(new Pojo("a", "b", "c", "d", "e", 1, 2, 3, 4, 5));
        long start = System.currentTimeMillis();
        int i = 0;
        for (; i < TIMES; i++) {
            Pojo p = new Pojo("a", "b", "c", "d", "e", 1, 2, 3, 4, 5);
            JSON.toJSONString(p);
        }
        long duration = System.currentTimeMillis() - start;
        log.info("run toJSONString {} TIMES cost: {}ms", TIMES, duration);
    }

    @Test
    public void toStringTest() {
        log.info("to string benchmark for {} times", TIMES);
        new Pojo("a", "b", "c", "d", "e", 1, 2, 3, 4, 5).toString();
        long start = System.currentTimeMillis();
        int i = 0;
        for (; i < TIMES; i++) {
            Pojo p = new Pojo("a", "b", "c", "d", "e", 1, 2, 3, 4, 5);
            p.toString();
        }
        long duration = System.currentTimeMillis() - start;
        log.info("run toString {} TIMES cost: {}ms", TIMES, duration);
    }

}
