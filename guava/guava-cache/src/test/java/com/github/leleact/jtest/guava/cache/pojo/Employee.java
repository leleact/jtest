package com.github.leleact.jtest.guava.cache.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Employee Pojo.
 *
 * @author leleact
 * @since 2025-03-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    /**
     * Employee id.
     */
    private int id;

    /**
     * Employee name.
     */
    private String name;
}
