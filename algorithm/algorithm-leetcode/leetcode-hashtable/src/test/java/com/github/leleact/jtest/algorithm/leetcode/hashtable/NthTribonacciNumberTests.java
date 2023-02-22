package com.github.leleact.jtest.algorithm.leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * N-th Tribonacci Number
 *
 * @author leleact
 * @since 2021-08-08
 */
public class NthTribonacciNumberTests {
    public static class Solution {
        private Map<Integer, Integer> store = new HashMap<>();

        public int tribonacci(int n) {
            if (n == 0) {
                return 0;
            }

            if (n == 1) {
                return 1;
            }

            if (n == 2) {
                return 1;
            }

            Integer stored = store.get(n);
            if (stored != null) {
                return stored;
            }

            int result = tribonacci(n - 3) + tribonacci(n - 2) + tribonacci(n - 1);
            store.put(n, result);
            return result;
        }
    }
}
