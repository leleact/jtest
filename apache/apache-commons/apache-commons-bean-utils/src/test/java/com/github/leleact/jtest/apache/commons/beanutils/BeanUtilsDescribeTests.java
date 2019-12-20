package com.github.leleact.jtest.apache.commons.beanutils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BeanUtilsDescribeTests {


    public static class MyBeanUtils extends BeanUtilsBean {

        public MyBeanUtils() {
            super(new MyConvertBean());
        }

        public Map<String, String> describe(Object bean)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
            if (bean == null) {
                return (new java.util.HashMap<>());
            }

            if (log.isDebugEnabled()) {
                log.debug("Describing bean: " + bean.getClass().getName());
            }
            Map<String, String> description = new HashMap<String, String>();

            PropertyDescriptor[] descriptors =
                getPropertyUtils().getPropertyDescriptors(bean);
            Class<?> clazz = bean.getClass();
            for (PropertyDescriptor descriptor : descriptors) {
                String name = descriptor.getName();
                if ("class".equals(name)) {
                    continue;
                }
                if (getReadMethod(clazz, descriptor) != null) {
                    description.put(name, getProperty(bean, name));
                }
            }
            return (description);
        }

        Method getReadMethod(Class<?> clazz, PropertyDescriptor descriptor) {
            return (MethodUtils.getAccessibleMethod(clazz, descriptor.getReadMethod()));
        }

        public String getNestedProperty(Object bean, String name)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

            Object value = getPropertyUtils().getNestedProperty(bean, name);
            return (getConvertUtils().convert(value, String.class)).toString();
        }
    }

    public static class MyConvertBean extends ConvertUtilsBean {
        @Override
        public void deregister() {
            super.deregister();
            Class<?> arrayType = Array.newInstance(A.B.class, 0).getClass();
            ArrayConverter arrayConverter = null;
            arrayConverter = new ArrayConverter(arrayType, new Converter() {
                @Override
                public <T> T convert(Class<T> type, Object value) {
                    if (type.equals(String.class)) {
                        A.B ele = (A.B) value;
                        return (T)("{age: " + ele.getAge() + ", name: " + ele.getName() + "}");
                    }
                    return null;
                }
            }, 0);
            arrayConverter.setOnlyFirstToString(false);
            register(arrayConverter, arrayType);
        }

        @Override
        public Object convert(Object value, Class<?> targetType) {

            Class<?> sourceType = value == null ? null : value.getClass();

            if (log.isDebugEnabled()) {
                if (value == null) {
                    log.debug("Convert null value to type '" +
                                  targetType.getName() + "'");
                } else {
                    log.debug("Convert type '" + sourceType.getName() + "' value '" + value +
                                  "' to type '" + targetType.getName() + "'");
                }
            }

            Object converted = value;
            Converter converter = lookup(sourceType, targetType, value);
            if (converter != null) {
                if (log.isTraceEnabled()) {
                    log.trace("  Using converter " + converter);
                }
                converted = converter.convert(targetType, value);
            }
            if (String.class.equals(targetType) && converted != null &&
                !(converted instanceof String)) {

                // NOTE: For backwards compatibility, if the Converter
                //       doesn't handle  conversion-->String then
                //       use the registered String Converter
                converter = lookup(String.class);
                if (converter != null) {
                    if (log.isTraceEnabled()) {
                        log.trace("  Using converter " + converter);
                    }
                    converted = converter.convert(String.class, converted);
                }

                // If the object still isn't a String, use toString() method
                if (converted != null && !(converted instanceof String)) {
                    converted = converted.toString();
                }

            }
            return converted;
        }

        public Converter lookup(Class<?> sourceType, Class<?> targetType, Object value) {
            if (targetType == null) {
                throw new IllegalArgumentException("Target type is missing");
            }
            if (sourceType == null) {
                return lookup(targetType);
            }

            Converter converter = null;
            // Convert --> String
            if (targetType == String.class) {
                converter = lookup(sourceType);
                if (converter == null && (sourceType.isArray() ||
                    Collection.class.isAssignableFrom(sourceType))) {
                    if (sourceType.isArray()) {
                        Class<?> componentType = sourceType.getComponentType();
                        Class<?> arrayType = Array.newInstance(componentType, 0).getClass();
                        converter = lookup(arrayType);
                    } else if (Collection.class.isAssignableFrom(sourceType)) {
                        Collection<?> collection = (Collection<?>) value;
                        if (collection == null || collection.isEmpty()) {
                            converter = null;
                        } else {
                            Class<?> componentType = collection.iterator().next().getClass();
                            Class<?> arrayType = Array.newInstance(componentType, 0).getClass();
                            converter = lookup(arrayType);
                        }
                    }
                    if (converter == null) {
                        converter = lookup(String[].class);
                    }
                }
                if (converter == null) {
                    converter = lookup(String.class);
                }
                return converter;
            }

            // Convert --> String array
            if (targetType == String[].class) {
                if (sourceType.isArray() || Collection.class.isAssignableFrom(sourceType)) {
                    converter = lookup(sourceType);
                }
                if (converter == null) {
                    converter = lookup(String[].class);
                }
                return converter;
            }
            return lookup(targetType);
        }
    }

    @Test
    public void describeTest() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        A a = new A();
        a.setName("a");
        a.setD(1.0);
        a.setDetails(Arrays.asList(new A.B("b1", 1), new A.B("b2", 2)));
        Map<String, String> map = BeanUtils.describe(a);
        log.info("describe class A: [{}]", map);
        Assertions.assertNotNull(map);
    }

    @Test
    public void myBeanUtilsTest() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        A a = new A();
        a.setName("a");
        a.setD(1.0);
        a.setDetails(Arrays.asList(new A.B("b1", 1), new A.B("b2", 2)));
        Map<String, String> map = new MyBeanUtils().describe(a);
        log.info("describe class A: [{}]", map);
        Assertions.assertNotNull(map);
    }
}
