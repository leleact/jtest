package com.github.leleact.jtest.rx.java;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObservableTests {

    private static final Logger log = LoggerFactory.getLogger(ObservableTests.class);

    @Test
    public void observableTest() {
        String[] letters = {"a", "b", "c", "d", "e", "f", "g"};
        Observable<String> observable = Observable.fromArray(letters);
        observable.publish().refCount();
        final String[] result = new String[]{""};
        observable.subscribe(i -> {
                log.info("i = {}", i);
                result[0] += i;
            },  //OnNext
            (Throwable error) -> {
                log.error(error.getMessage(), error);
                error.getStackTrace();
            } //OnError
            , () -> result[0] += "_Completed" //OnCompleted
        );
        assertEquals("abcdefg_Completed", result[0]);
    }

    @Test
    public void multipleObsTest() {
        Observable<String> observable = Observable.fromArray(new String[]{"a", "b", "c"});
        ConnectableObservable<String> connObs = observable.publish();
        connObs.subscribe(i -> log.info("{}", i), error -> log.error(error.getMessage(), error),
            () -> log.info("__complete 0"));

        connObs.subscribe(i -> log.info("{}", i), error -> log.error(error.getMessage(), error),
            () -> log.info("__complete 1"));
        connObs.connect();
    }
}
