package com.github.leleact.jtest.jdk.optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalTests {

    @Test
    public void objAcquireTest() {
        Optional<Car> optional = Optional.of(new Car("1"));
        Car car = optional.map(id -> new Car(id.getId()))
                          .orElseGet(() -> new Car("2"));
        Assertions.assertEquals("1", car.getId());
    }

}
