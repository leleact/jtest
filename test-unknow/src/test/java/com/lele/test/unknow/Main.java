package com.lele.test.unknow;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    @Test
    public void test1() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(10);
        String s = "NIIKHFIUWHIUFHOIWJHFOIJHWOIFHOIWJHFIOJHWOFJ";

        bos.write(s.getBytes());

        System.out.println("size:" + s.getBytes().length);
        System.out.println("size:" + bos.toByteArray().length);
    }
}
