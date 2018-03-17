package com.lele.test.jdk.file;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class FileTest {

    private static Logger log = LoggerFactory.getLogger(FileTest.class);

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
