package com.github.leleact.jtest.jdk.zip;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipTestor {

    private String fileName = "C:\\Users\\vacp\\Desktop\\1.txt";

    private String zipFileName = "C:\\Users\\vacp\\Desktop\\1.zip";

    @Before
    public void createFile() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
        randomAccessFile.write("aaa".getBytes());
        randomAccessFile.close();
    }

    @Test
    public void zipTest() {
        try (
                FileInputStream input = new FileInputStream(fileName);
                ZipOutputStream output = new ZipOutputStream(new FileOutputStream(zipFileName))

        ) {
            byte[] buffer = new byte[4096];
            int n = 0;
            output.putNextEntry(new ZipEntry("heihei"));
            while(-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void zipTestToBase64() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(4096);
        try (
                FileInputStream input = new FileInputStream(fileName);
                ZipOutputStream output = new ZipOutputStream(bos)

        ) {
            byte[] buffer = new byte[4096];
            int n = 0;
            output.putNextEntry(new ZipEntry("heihei"));
            while(-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String base64Str = new String(Base64.getEncoder().encode(bos.toByteArray()));
        System.out.println("size: " + bos.size());
        System.out.println(base64Str);
        System.out.println("size: " + base64Str.getBytes().length);

    }

}
