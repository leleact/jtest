package com.lele.test.http.loadbalance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class LoadBalance<T> {

    private List<T> listItems = new ArrayList<>();

    public LoadBalance(List<T> args) {
        listItems = args;
    }

    public LoadBalance(T... args) {
        this(Arrays.asList(args));
    }

    public abstract T selectItem();

    public List<T> getListItems() {
        return listItems;
    }

    public void setListItems(List<T> listItems) {
        this.listItems = listItems;
    }
}
