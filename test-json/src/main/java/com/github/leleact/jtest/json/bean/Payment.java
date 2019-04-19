package com.github.leleact.jtest.json.bean;

public class Payment {

    private String category;

    private float fee;

    public Payment() {
    }

    public Payment(String category, float fee) {
        this.category = category;
        this.fee = fee;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }
}
