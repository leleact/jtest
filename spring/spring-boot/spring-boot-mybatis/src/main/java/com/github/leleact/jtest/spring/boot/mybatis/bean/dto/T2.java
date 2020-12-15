package com.github.leleact.jtest.spring.boot.mybatis.bean.dto;

public class T2 {

    private String f01;

    private String f02;

    public String getF01() {
        return f01;
    }

    public void setF01(String f01) {
        this.f01 = f01;
    }

    public String getF02() {
        return f02;
    }

    public void setF02(String f02) {
        this.f02 = f02;
    }

    @Override
    public String toString() {
        return "T2{" +
                "f01='" + f01 + '\'' +
                ", f02='" + f02 + '\'' +
                '}';
    }
}
