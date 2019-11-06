package com.github.leleact.jtest.jdk.file;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

@Slf4j
public class FileTests {

    // 递归创建目录
    @Test
    public void mkdirsTest() {
        String path = "C:\\Users\\Lele\\Desktop\\a";
        File file = new File(path);
        boolean ret = file.mkdirs();
        log.info("ret=[" + ret + "]");
    }

    @Test
    public void generatorFile() throws IOException {
        String filepath = "C:\\Users\\Lele\\Desktop\\a.txt";
        File f = new File(filepath);
    }
}
