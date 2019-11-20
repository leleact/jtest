package com.github.leleact.jtest.jdk.type;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Slf4j
public class TypeUtils {

    private TypeUtils() {
    }

    public static void showType(Class<?> clazz) {
        showGenericInterfaceType(clazz);
        showGenericSuperType(clazz);
    }

    public static void showGenericInterfaceType(Class<?> clazz) {
        log.info("show type [{}] generic interface types ===== START =====", clazz);
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        for (Type t : genericInterfaces) {
            ParameterizedType type = (ParameterizedType) t;
            Type[] typeArgs = type.getActualTypeArguments();
            log.debug("name of type [{}] is [{}]", type, type.getTypeName());
            for (Type arg : typeArgs) {
                log.debug("actual type of type [{}] is [{}]", type, arg);
            }
            log.debug("owner type of type [{}] is [{}}]", type, type.getOwnerType());
            log.debug("raw type of type [{}] is [{}]", type, type.getRawType());
        }
        log.info("show type [{}] generic interface types ===== END =====", clazz);
    }

    public static void showGenericSuperType(Class<?> clazz) {
        log.info("show type [{}] super class types ===== START =====", clazz);
        Type superClazz = clazz.getGenericSuperclass();
        log.debug("name of type [{}] is [{}]", superClazz, superClazz.getTypeName());
        if (superClazz instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
            Type[] typeArgs = type.getActualTypeArguments();
            for (Type arg : typeArgs) {
                log.debug("actual type of type [{}] is [{}]", type, arg);
            }
            log.debug("owner type of type [{}] is [{}}]", type, type.getOwnerType());
            log.debug("raw type of type [{}] is [{}]", type, type.getRawType());
        }
        log.info("show type [{}] super class types ===== END =====", clazz);
    }

}
