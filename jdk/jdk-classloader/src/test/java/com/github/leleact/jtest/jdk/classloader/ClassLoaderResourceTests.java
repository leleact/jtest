package com.github.leleact.jtest.jdk.classloader;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class ClassLoaderResourceTests {

    @Test
    public void classLoaderAsStreamTest() throws IOException {
        // only find the first one
        try (InputStream is = ClassLoaderResourceTests.class.getClassLoader()
                                                            .getResourceAsStream("META-INF/MANIFEST.MF")) {
            if (is == null) {
                log.error("META-INF/MANIFEST.MF not found");
            }
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            for (int length; (length = is.read(buffer)) != -1; ) {
                result.write(buffer, 0, length);
            }
            log.info(result.toString());
        }
    }
}
