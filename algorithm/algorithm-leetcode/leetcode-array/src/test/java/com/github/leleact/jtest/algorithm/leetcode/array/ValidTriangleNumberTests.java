package com.github.leleact.jtest.algorithm.leetcode.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Valid Triangle Number
 *
 * @author leleact
 * @since 2021-08-04
 */
@Slf4j
public class ValidTriangleNumberTests {
    public static class Solution {
        public int triangleNumber(int[] nums) {
            Arrays.sort(nums);
            int i;
            int j;
            int k;
            int c = 0;
            for (i = 0; i < nums.length - 2; i++) {
                for (j = i + 1; j < nums.length - 1; j++) {
                    for (k = j + 1; k < nums.length; k++) {
                        if (nums[i] + nums[j] > nums[k]) {
                            c++;
                        }
                    }
                }
            }
            return c;
        }
    }

    @Test
    public void solutionTest() {
        int[] nums = {2, 2, 3, 4};
        log.info("{}", new Solution().triangleNumber(nums));
    }
}
