package com.github.leleact.jtest.spring.schedule;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledFuture;

@EnableScheduling
@Configuration
@Data
public class ScheduleJobApplication {

    private ConcurrentMap<String, ScheduledFuture<?>> futures = new ConcurrentHashMap<>();

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }

    public Job addJob(String name) {
        Job job = new Job(name);
        ScheduledFuture<?> future = taskScheduler().schedule(job , new CronTrigger("0/1 * * * * *"));
        futures.put(name, future);
        return job;
    }

    public void cancel(String name) {
        futures.remove(name).cancel(false);
    }
}
