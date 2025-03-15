package com.github.leleact.jtest.javassist;

import javassist.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * use javassist modify a exist java class
 *
 * @author leleact
 * @since 2022-03-13
 */
@Slf4j
public class ModifyClazzTests {
    @Slf4j
    public static class EnhancePojo {
        public Integer f1(String parameter) {
            log.info("input parameter is : {}", parameter);
            return 1;
        }
    }

    @Disabled
    @Test
    public void modifyMethodTest() throws NotFoundException, CannotCompileException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // junit parse ModifyClazzTests will load EnhancePojo, so when run test Exception will occur
        ClassPool pool = ClassPool.getDefault();
        //pool.insertClassPath(new ClassClassPath(EnhancePojo.class));
        CtClass cc = pool.get("com.github.leleact.jtest.javassist.ModifyClazzTests$EnhancePojo");

        CtMethod enhanceF1 = cc.getDeclaredMethod("f1");
        enhanceF1.insertBefore("log.info(\"before modify method\");");
        enhanceF1.insertAfter("log.info(\"after modify method\");");

        EnhancePojo pojo = (EnhancePojo) cc.toClass(ModifyClazzTests.class).getConstructor().newInstance();
        Assertions.assertEquals(1,pojo.f1("hello"));
    }
}
