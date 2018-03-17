package com.lele.test.exception;

/**
 * Created by Lele on 2017/7/6.
 */
public class MethodThrowExceptions {

    public enum ExceptionType {
        NullExceptionType,
        IndexOutOfBoundsExceptionType,
        RuntimeExceptionType,
        ExceptionType
    }

    /**
     * unckeck异常 无需声明或try{} catch()
     */
    public static void methodThrowUncheckException(ExceptionType et) {
        if (et.equals(ExceptionType.NullExceptionType)) {
            throw new NullPointerException();
        } else if (et.equals(ExceptionType.IndexOutOfBoundsExceptionType)) {
            throw new IndexOutOfBoundsException();
        } else if (et.equals(ExceptionType.RuntimeExceptionType)) {
            throw new RuntimeException();
        } else {
            /**
             * 如果增加这行代码，则需要在函数签名上增加throw
             */
            //throw new Exception();
        }
    }


    public static void methodThrowCheckEception() throws Exception {
        throw new Exception();
    }

}
