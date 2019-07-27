package com.github.leleact.jtest.dubbo.api.bean.response;

import com.github.leleact.jtest.dubbo.api.bean.request.CommonRequest;

import java.io.Serializable;

public class CommonResponse extends CommonRequest implements Serializable {

    private int res;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "res='" + res +
                "\', super=\'" + super.toString() + "\'}'";
    }
}
