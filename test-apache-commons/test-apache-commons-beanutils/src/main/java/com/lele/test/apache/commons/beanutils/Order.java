package com.lele.test.apache.commons.beanutils;

import java.util.Date;

public class Order {

    private String pri;

    private String f1;

    private String f2;

    private int i1;

    private int i2;

    private Date d1;

    private Date d2;

    private char c1;

    private char c2;

    public String getPri() {
        return pri;
    }

    public void setPri(String pri) {
        this.pri = pri;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    public int getI1() {
        return i1;
    }

    public void setI1(int i1) {
        this.i1 = i1;
    }

    public int getI2() {
        return i2;
    }

    public void setI2(int i2) {
        this.i2 = i2;
    }

    public Date getD1() {
        return d1;
    }

    public void setD1(Date d1) {
        this.d1 = d1;
    }

    public Date getD2() {
        return d2;
    }

    public void setD2(Date d2) {
        this.d2 = d2;
    }

    public char getC1() {
        return c1;
    }

    public void setC1(char c1) {
        this.c1 = c1;
    }

    public char getC2() {
        return c2;
    }

    public void setC2(char c2) {
        this.c2 = c2;
    }

    @Override
    public String toString() {
        return "Order{" +
                "pri='" + pri + '\'' +
                ", f1='" + f1 + '\'' +
                ", f2='" + f2 + '\'' +
                ", i1=" + i1 +
                ", i2=" + i2 +
                ", d1=" + d1 +
                ", d2=" + d2 +
                ", c1=" + c1 +
                ", c2=" + c2 +
                '}';
    }
}
