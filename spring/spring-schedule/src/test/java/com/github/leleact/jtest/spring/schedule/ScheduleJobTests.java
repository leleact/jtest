package com.github.leleact.jtest.spring.schedule;


import lombok.extern.slf4j.Slf4j;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.annotation.Resource;

import static org.awaitility.Awaitility.await;

@Slf4j
@SpringJUnitConfig(ScheduleJobApplication.class)
class ScheduleJobTests {

    @Resource
    private ScheduleJobApplication scheduleJobApplication;

    @Test
    void addScheduleJobTest() {
        await().untilAsserted(() -> {
            scheduleJobApplication.addJob("a");
            scheduleJobApplication.cancel("a");
            Job job = scheduleJobApplication.addJob("b");
            Assertions.assertEquals(1, scheduleJobApplication.getFutures().size());
            await().untilAtomic(job.getCounter(), new IsEqual<>(3));
        });
    }
}
