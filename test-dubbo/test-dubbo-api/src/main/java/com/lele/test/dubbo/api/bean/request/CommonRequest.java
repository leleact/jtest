package com.lele.test.dubbo.api.bean.request;

import java.io.Serializable;
import java.util.Map;

public class CommonRequest implements Serializable {

    private String s;

    private Integer i;

    private Boolean b;

    private Long l;

    private Double d;

    private Map<String, String> m;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public Boolean getB() {
        return b;
    }

    public void setB(Boolean b) {
        this.b = b;
    }

    public Long getL() {
        return l;
    }

    public void setL(Long l) {
        this.l = l;
    }

    public Double getD() {
        return d;
    }

    public void setD(Double d) {
        this.d = d;
    }

    public Map<String, String> getM() {
        return m;
    }

    public void setM(Map<String, String> m) {
        this.m = m;
    }

    @Override
    public String toString() {
        return "CommonRequest{" +
                "s='" + s + '\'' +
                ", i=" + i +
                ", b=" + b +
                ", l=" + l +
                ", d=" + d +
                ", m=" + m +
                '}';
    }
}
