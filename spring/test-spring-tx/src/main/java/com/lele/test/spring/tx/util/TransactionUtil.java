package com.lele.test.spring.tx.util;

import org.springframework.transaction.annotation.Transactional;

public class TransactionUtil {

    @Transactional
    public <V> V transaction(TransactionCall<V> call) {
        return call.call();
    }
}
