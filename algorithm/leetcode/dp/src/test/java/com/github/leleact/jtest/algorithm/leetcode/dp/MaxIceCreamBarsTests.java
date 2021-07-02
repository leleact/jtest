package com.github.leleact.jtest.algorithm.leetcode.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * max bag problem
 * <p>
 * leetcode No.1833
 *
 * @author leleact
 * @since 2021-07-02
 */
@Slf4j
public class MaxIceCreamBarsTests {
    class Solution {
        public int maxIceCream(int[] costs, int coins) {
            Arrays.sort(costs);
            if (costs[0] > coins) {
                return 0;
            }
            int i;
            int left = coins;
            int count = 0;
            for (i = 0; i < costs.length; i++) {
                if (costs[i] <= left) {
                    left = left - costs[i];
                    count++;
                } else {
                    break;
                }
            }
            return count;
        }
    }

    @Test
    public void solutionTest() {
        int[] costs = new int[]{1, 6, 3, 1, 2, 5};
        int coins = 20;
        Solution solution = new Solution();
        int c = solution.maxIceCream(costs, coins);
        log.info("c: {}", c);
    }
}
