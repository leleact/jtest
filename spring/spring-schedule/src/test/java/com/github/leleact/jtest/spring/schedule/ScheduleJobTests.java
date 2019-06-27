package com.github.leleact.jtest.spring.schedule;


import lombok.extern.slf4j.Slf4j;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.support.CronTrigger;
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
            scheduleJobApplication.addJob("a", new CronTrigger("0/1 * * * * *"));
            scheduleJobApplication.cancel("a");
            Job job = scheduleJobApplication.addJob("b", new CronTrigger("0/1 * * * * *"));
            Assertions.assertEquals(1, scheduleJobApplication.getFutures().size());
            await().untilAtomic(job.getCounter(), new IsEqual<>(3));
        });
    }
}
