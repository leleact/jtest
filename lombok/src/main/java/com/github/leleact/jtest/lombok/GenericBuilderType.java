package com.github.leleact.jtest.lombok;

import lombok.Builder;
import lombok.Getter;

/**
 * generic type builder pattern
 *
 * @author leleact
 * @since 2023-03-05
 */
@Builder
@Getter
public class GenericBuilderType<T> {
    private Class<T> clazz;

    private T instance;
}
