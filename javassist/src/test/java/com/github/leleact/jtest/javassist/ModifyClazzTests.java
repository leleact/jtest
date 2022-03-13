package com.github.leleact.jtest.javassist;

import javassist.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * use javassist modify a exist java class
 *
 * @author leleact
 * @since 2022-03-13
 */

public class ModifyClazzTests {
    public static class Pojo {
        private static final Logger log = LoggerFactory.getLogger(Pojo.class);

        public Integer f1(String string) {
            log.info("input string is : {}", string);
            return 1;
        }
    }

    @Test
    public void modifyMethodTest() throws NotFoundException, CannotCompileException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("com.github.leleact.jtest.javassist.ModifyClazzTests$Pojo");

        CtMethod enhanceF1 = cc.getDeclaredMethod("f1");
        enhanceF1.insertBefore("log.info(\"before modify method\");");
        enhanceF1.insertAfter("log.info(\"after modify method\");");


        Pojo pojo = (Pojo) cc.toClass(ModifyClazzTests.class).getConstructor().newInstance();
        Assertions.assertEquals(pojo.f1("hello"), 1);
    }
}
