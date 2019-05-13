package com.github.leleact.jtest.json.bean;

import java.util.Map;

public class Bean {

    private String field1;

    private int field2;

    private Map<String, Payment> payment;

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public int getField2() {
        return field2;
    }

    public void setField2(int field2) {
        this.field2 = field2;
    }

    public Map<String, Payment> getPayment() {
        return payment;
    }

    public void setPayment(Map<String, Payment> payment) {
        this.payment = payment;
    }
}
