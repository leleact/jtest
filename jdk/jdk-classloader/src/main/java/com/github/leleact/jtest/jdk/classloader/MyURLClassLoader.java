package com.github.leleact.jtest.jdk.classloader;

import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

/**
 * my classloader
 *
 * @author leleact
 * @since 2021-01-22
 */
@Slf4j
public class MyURLClassLoader extends URLClassLoader {

    public MyURLClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public MyURLClassLoader(URL[] urls) {
        super(urls);
    }

    public MyURLClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
