package com.github.leleact.jtest.validator.datadic;

/**
 * validator dictionary.
 *
 * @author leleact
 * @since 1.0
 */
public enum ValidateDic {

    NAME {
        @Override
        public int min() {
            return 2;
        }

        @Override
        public int max() {
            return 12;
        }
    };

    public int min() {
        throw new AbstractMethodError();
    }

    public int max() {
        throw new AbstractMethodError();
    }
}
