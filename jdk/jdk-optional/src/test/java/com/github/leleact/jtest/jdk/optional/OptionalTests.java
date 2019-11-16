package com.github.leleact.jtest.jdk.optional;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

@Slf4j
public class OptionalTests {

    @Test
    public void objAcquireTest() {
        Optional<Car> optional = Optional.of(new Car("1"));
        Car car = optional.map(id -> new Car(id.getId()))
                          .orElseGet(() -> new Car("2"));
        Assertions.assertEquals("1", car.getId());
    }

}
