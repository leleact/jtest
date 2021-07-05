package com.github.leleact.jtest.algorithm.leetcode.binary;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * find error nums
 *
 * @author leleact
 * @since 2021-07-04
 */
@Slf4j
public class ErrorNumsTests {
    class Solution {
        public int[] findErrorNums(int[] nums) {
            Arrays.sort(nums);
            int[] result = new int[2];
            int i;
            for (i = 0; i < nums.length; i++) {
                if (nums[0] != 1) {
                    result[1] = 1;
                } else if (i + 1 != nums[i]) {
                    result[1] = i + 1;
                }
                if (i != 0 && nums[i] - nums[i - 1] == 0) {
                    result[0] = nums[i];
                }
            }
            return result;
        }
    }

    @Test
    public void solutionTest() {
        //int[] nums = new int[]{1, 5, 3, 2, 2, 7, 6, 4, 8, 9};
        int[] nums = new int[]{2, 3, 3, 4, 5, 6};
        // 1 2 2 3 4 5 6 7 8 9
        // 2 3 3 4 5 6
        log.info("{}", new Solution().findErrorNums(nums));
    }
}
