package com.github.leleact.jtest.algorithm.leetcode.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Sum of All Odd Length Subarrays
 * <p>
 * Leetcode No.114
 *
 * @author leleact
 * @since 2021-08-29
 */
public class SumOfAllOddLengthSubarraysTests {
    public static class Solution {
        public int sumOddLengthSubarrays(int[] arr) {
            int length = arr.length;
            if (length == 0) {
                return 0;
            }
            if (length == 1) {
                return arr[0];
            }
            int maxOdd = length;
            if (length % 2 == 0) {
                maxOdd = length - 1;
            }
            int sum = 0;
            int idx = 0;
            for (idx = 1; idx <= maxOdd; idx = idx + 2) {
                int left = 0;
                int right = left + idx;
                while (right <= length) {
                    int start = left;
                    for (start = left; start < right; start++) {
                        sum = sum + arr[start];
                    }
                    left++;
                    right++;
                }
            }
            return sum;
        }
    }

    @Test
    public void solutionTest() {
        Assertions.assertEquals(58, new Solution().sumOddLengthSubarrays(new int[]{1, 4, 2, 5, 3}));
        Assertions.assertEquals(3, new Solution().sumOddLengthSubarrays(new int[]{1, 2}));
        Assertions.assertEquals(66, new Solution().sumOddLengthSubarrays(new int[]{10, 11, 12}));
    }
}
