package com.github.leleact.jtest.jdk.classloader;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sun.net.www.ParseUtil;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * my classloader tests
 *
 * @author leleact
 * @since 2021-01-22
 */
@Slf4j
class MyURLClassLoaderTests {

    @Test
    void variableTest() throws MalformedURLException, ClassNotFoundException {
        final String classPath = System.getProperty("java.class.path");
        log.debug("{}", classPath);
        int index = 0;
        int nextIndex = 0;
        int count = 0;

        for (index = 0; (nextIndex = classPath.indexOf(File.pathSeparator, index)) != -1; index = nextIndex + 1) {
            count++;
        }

        File[] files = new File[count];
        int fileIndex = 0;
        for (index = 0; (nextIndex = classPath.indexOf(File.pathSeparator, index)) != -1; index = nextIndex + 1) {
            log.info("full file name: {}", classPath.substring(index, nextIndex));
            if (nextIndex - index > 0) {
                files[fileIndex++] = new File(classPath.substring(index, nextIndex));
            } else {
                files[fileIndex++] = new File(".");
            }
        }
        URL[] urls = new URL[files.length];
        for (int i = 0; i < files.length; i++) {
            urls[i] = ParseUtil.fileToEncodedURL(files[i]);
        }

        for (URL url : urls) {
            log.info("url: {}", url);
        }

        MyURLClassLoader cl = new MyURLClassLoader(urls);
        Class<?> clazz = cl.loadClass("com.github.leleact.jtest.jdk.classloader.Pojo");
        log.info("clazz: {}", clazz.getName());
        Assertions.assertEquals("com.github.leleact.jtest.jdk.classloader.Pojo", clazz.getName());
    }
}
