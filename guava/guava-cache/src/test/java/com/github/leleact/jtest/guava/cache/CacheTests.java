package com.github.leleact.jtest.guava.cache;

import com.github.leleact.jtest.guava.cache.pojo.Employee;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CacheTests {

    @Test
    public void createCacheTest() throws ExecutionException, InterruptedException {
        LoadingCache<Integer, Employee> empCache = CacheBuilder.newBuilder()
                                                               .maximumSize(100)
                                                               .expireAfterWrite(10, TimeUnit.MINUTES)
                                                               .build(new CacheLoader<>() {
                                                                   @Override
                                                                   public Employee load(Integer id) {
                                                                       return getEmployeeById(id);
                                                                   }
                                                               });
        log.info(empCache.get(1).getName());
        Thread.sleep(3000);
        log.info(empCache.get(1).getName());
    }

    private static Employee getEmployeeById(int id) {
        log.info("--Executing getEmployeeById--");
        //Perform any expensive task like fetching data from Database.
        //For the demo we are performing a simple task
        Employee emp1 = new Employee(1, "Ramesh");
        Employee emp2 = new Employee(2, "Mohan");
        if (id == 1) {
            return emp1;
        } else {
            return emp2;
        }
    }
}
