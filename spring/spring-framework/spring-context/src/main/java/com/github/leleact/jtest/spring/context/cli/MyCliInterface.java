package com.github.leleact.jtest.spring.context.cli;

import com.github.leleact.jtest.spring.context.annotation.MyCli;

/**
 * cli interface
 *
 * @author leleact
 * @since 2021-01-09
 */
@MyCli(url = "https://github.com")
public interface MyCliInterface {
    String echo(String name);
}
