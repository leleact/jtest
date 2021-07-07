package com.github.leleact.jtest.algorithm.leetcode.binary;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Count Good Meals
 * <p>
 * leetcode No.1711
 *
 * @author leleact
 * @since 2021-07-07
 */
@Slf4j
public class CountGoodMealsTests {
    class Solution {
        // Timeout
        public int countPairs(int[] deliciousness) {
            int i;
            int j;
            int count = 0;
            for (i = 0; i < deliciousness.length - 1; i++) {
                for (j = i + 1; j < deliciousness.length; j++) {
                    int sum = deliciousness[i] + deliciousness[j];
                    if (sum != 0 && ((sum & sum - 1) == 0)) {
                        count++;
                    }
                }
            }
            return count;
        }
    }

    class Solution1 {

        public int countPairs(int[] deliciousness) {
            final int MOD = 1000000007;
            // 获取最大值
            int maxVal = 0;
            for (int val : deliciousness) {
                maxVal = Math.max(maxVal, val);
            }
            int maxSum = maxVal * 2;
            int pairs = 0;
            // val 有多少个
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            int n = deliciousness.length;
            for (int i = 0; i < n; i++) {
                int val = deliciousness[i];
                // 枚举所有和
                for (int sum = 1; sum <= maxSum; sum <<= 1) {
                    int count = map.getOrDefault(sum - val, 0);
                    pairs = (pairs + count) % MOD;
                }
                map.put(val, map.getOrDefault(val, 0) + 1);
            }
            return pairs;
        }
    }

    @Test
    public void solutionTest() {
        int[] deliciousness = new int[]{2160, 1936, 3, 29, 27, 5, 2503, 1593, 2, 0, 16, 0, 3860, 28908, 6, 2, 15, 49, 6246, 1946, 23, 105, 7996, 196, 0, 2, 55, 457, 5, 3, 924, 7268, 16, 48, 4, 0, 12, 116, 2628, 1468};
        int count = new Solution().countPairs(deliciousness);
        log.info("result: {}", count);
    }
}
