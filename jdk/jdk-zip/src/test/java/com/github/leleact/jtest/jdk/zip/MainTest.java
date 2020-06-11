package com.github.leleact.jtest.jdk.zip;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Slf4j
public class MainTest {


    // 解压单文件zip包
    @Test
    public void compressTest() throws IOException {
        String localFile = "C:\\Users\\Lele\\Desktop\\1.zip";
        final int BUFFER = 2048;
        try {
            BufferedOutputStream dest = null;
            FileInputStream fis = new
                FileInputStream(localFile);
            ZipInputStream zis = new
                ZipInputStream(new BufferedInputStream(fis));
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                log.info("Extracting: " + entry);
                int count;
                byte[] data = new byte[BUFFER];
                // write the files to the disk
                FileOutputStream fos = new
                    FileOutputStream(entry.getName());
                dest = new
                    BufferedOutputStream(fos, BUFFER);
                while ((count = zis.read(data, 0, BUFFER))
                    != -1) {
                    dest.write(data, 0, count);
                }
                dest.flush();
                dest.close();
            }
            zis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deCompressTest() {
        Assertions.assertDoesNotThrow(() -> {
            String localFile = "C:\\Users\\Lele\\Desktop\\a.zip";
            ZipUtils.unzip1(localFile);
        });
    }

}
