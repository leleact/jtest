package com.lele.test.http.loadbalance;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinLoadBalance<T> extends LoadBalance<T> {

    private final AtomicInteger sequence = new AtomicInteger(0);

    public RoundRobinLoadBalance(List<T> args) {
        super(args);
    }

    @Override
    public T selectItem() {
        int r = sequence.getAndIncrement();
        int m = r % getListItems().size();
        return getListItems().get(m);
    }
}
