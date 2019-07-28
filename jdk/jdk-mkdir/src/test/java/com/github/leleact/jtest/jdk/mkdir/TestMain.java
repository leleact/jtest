package com.github.leleact.jtest.jdk.mkdir;

import org.junit.Test;

import java.io.File;

public class TestMain {

    @Test
    public void mkdirTest() {
        String s = "a/b/c";
        File f = new File(s);
        f.mkdir();
    }

    @Test
    public void mkdirsTest() {
        String s = "a/b/c";
        File f = new File(s);
        f.mkdirs();
    }

}
