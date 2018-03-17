package com.lele.test.database.mysql.test;

import com.lele.test.database.mysql.bean.mapper.SequenceMapper;
import com.lele.test.database.mysql.bean.mapper.SequencePerDayMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TransactionTestor {

    private static final Logger log = LoggerFactory.getLogger(TransactionTestor.class);

    @Resource
    private SequencePerDayMapper sequencePerDayMapper;

    @Test
    public void getMchtNoTest() throws InterruptedException {

        Map<Integer, Integer> map = new ConcurrentHashMap<>();

        List<Thread> threads = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    int ret = sequencePerDayMapper.selectMchtNo();
                    if (map.containsKey(ret)) {
                        throw new RuntimeException("键值重复");
                    }
                    map.put(ret, ret);
                }
            });
            threads.add(t);
            t.start();
        }
        for(Thread t : threads) {
            t.join();
        }
        log.info("最终数据为:{}", map.size());
    }

    @Resource
    private SequenceMapper sequenceMapper;

    @Test
    public void getUserIdTest() throws InterruptedException {

        Map<Integer, Integer> map = new ConcurrentHashMap<>();

        List<Thread> threads = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    int ret = sequenceMapper.selectUserId("testId");
                    if (map.containsKey(ret)) {
                        throw new RuntimeException("键值重复");
                    }
                    map.put(ret, ret);
                }
            });
            threads.add(t);
            t.start();
        }
        for(Thread t : threads) {
            t.join();
        }
        log.info("最终数据为:{}", map.size());
    }

}
