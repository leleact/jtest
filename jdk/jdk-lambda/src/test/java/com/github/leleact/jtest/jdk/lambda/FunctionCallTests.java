package com.github.leleact.jtest.jdk.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
class FunctionCallTests {

    class A {
        private String str;

        A(String str) {
            this.str = str;
        }

        void execute(Caller caller) {
            caller.call(str);
        }
    }

    @Test
    void callTest() {
        A a = new A("Hello Word!!!");
        a.execute(log::info);
    }

    @Test
    void listTest() {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.forEach(log::info);
    }
}
