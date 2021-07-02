package com.github.leleact.jtest.algorithm.leetcode.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Continuous Subarray Sum
 * <p>
 * leetcode No.523
 *
 * @author leleact
 * @since 2021-06-16
 */
@Slf4j
class ContinuousSubarraySumTest {
    @Test
    void solutionTest() {
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < nums.length; i++) {
            remainder = (remainder + nums[i]) % k;
            if (map.containsKey(remainder)) {
                int prevIndex = map.get(remainder);
                if (i - prevIndex >= 2) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }

    // timeout
    public boolean checkSubarraySum1(int[] nums, int k) {
        int m;
        int n;
        for (m = 0; m < nums.length - 1; m++) {
            for (n = m + 1; n < nums.length; n++) {
                int sum = 0;
                int idx;
                for (idx = m; idx <= n; idx++) {
                    sum = sum + nums[idx];
                }
                if (sum % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
