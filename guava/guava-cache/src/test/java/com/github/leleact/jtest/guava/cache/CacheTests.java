package com.github.leleact.jtest.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
class CacheTests {

    private static class Employee {
        private int id;
        private String name;

        public Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
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


    @Test
    void createCacheTest() throws ExecutionException, InterruptedException {

        LoadingCache<Integer, Employee> empCache = CacheBuilder.newBuilder()
                                                               .maximumSize(100)
                                                               .expireAfterWrite(10, TimeUnit.MINUTES)
                                                               .build(
                                                                   new CacheLoader<Integer, Employee>() {
                                                                       @Override
                                                                       public Employee load(Integer id) throws Exception {
                                                                           return getEmployeeById(id);
                                                                       }
                                                                   }
                                                               );

        log.info(empCache.get(1).getName());

        Thread.sleep(3000);

        log.info(empCache.get(1).getName());
    }
}
