package com.github.leleact.jtest.http.loadbalance;

import java.util.List;
import java.util.Random;

public class RandomLoadBalance<T> extends LoadBalance<T> {

    private final Random random = new Random();

    public RandomLoadBalance(List<T> args) {
        super(args);
    }

    @Override
    public T selectItem() {
        int r = random.nextInt();
        int m = r % getListItems().size();
        return getListItems().get(m);
    }
}
