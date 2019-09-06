package com.github.leleact.jtest.jdk.lambda;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

class BiFunctionTests {

    class A {
    }

    class B {
    }

    class C {
    }

    class D {
    }

    class E {
    }

    class F {
    }

    class G {
    }

    class H {
    }

    class I {
    }

    /**
     * 组合算子为 (A, B) -> F
     */
    @Test
    void biFunctionTest() {
        BiFunction<A, B, C> bi = (A a, B b) -> new C();
        F f = bi.andThen((C c) -> new D()).andThen((D d) -> new E()).andThen((E e) -> new F()).apply(new A(), new B());
    }

}
