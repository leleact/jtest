package com.github.leleact.jtest.jdk.zip;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

@Slf4j
public class ZipUtils {

    private static final String DEFAULT_DEST_FOLDER = ZipUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();

    private static final int BUFFER = 2048;

    public static void unzip1(String fileName) throws IOException {
        String canonicalDestinationDirPath = new File(DEFAULT_DEST_FOLDER).getCanonicalPath();
        try {
            BufferedOutputStream dest = null;
            FileInputStream fis = new FileInputStream(fileName);
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                log.info("Extracting: " + entry);
                if (entry.isDirectory()) {
                    File f = new File(DEFAULT_DEST_FOLDER + entry.getName());
                    String canonicalDestinationFile = f.getCanonicalPath();
                    if (!canonicalDestinationFile.startsWith(canonicalDestinationDirPath + File.separator)) {
                        throw new RuntimeException("Entry is outside of the target dir: " + entry.getName());
                    }
                    if (!f.exists()) {
                        f.mkdirs();
                    }
                    continue;
                }
                int count;
                byte[] data = new byte[BUFFER];
                // write the files to the disk
                FileOutputStream fos = new FileOutputStream(entry.getName());
                dest = new BufferedOutputStream(fos, BUFFER);
                while ((count = zis.read(data, 0, BUFFER)) != -1) {
                    dest.write(data, 0, count);
                }
                dest.flush();
                dest.close();
            }
            zis.close();
        } catch (Exception e) {
            log.error("unzip error!", e);
        }
    }

    public static void unzip2(String fileName) {
        try {
            BufferedOutputStream dest = null;
            BufferedInputStream is = null;
            ZipEntry entry;
            ZipFile zipfile = new ZipFile(fileName);
            Enumeration<?> e = zipfile.entries();
            while (e.hasMoreElements()) {
                entry = (ZipEntry) e.nextElement();
                log.info("Extracting: " + entry);
                is = new BufferedInputStream(zipfile.getInputStream(entry));
                int count;
                byte[] data = new byte[BUFFER];
                FileOutputStream fos = new FileOutputStream(entry.getName());
                dest = new BufferedOutputStream(fos, BUFFER);
                while ((count = is.read(data, 0, BUFFER)) != -1) {
                    dest.write(data, 0, count);
                }
                dest.flush();
                dest.close();
                is.close();
            }
        } catch (Exception e) {
            log.error("unzip error!", e);
        }
    }
}
