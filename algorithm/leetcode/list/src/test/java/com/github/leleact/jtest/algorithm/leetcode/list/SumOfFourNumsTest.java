package com.github.leleact.jtest.algorithm.leetcode.list;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * sum of four nums
 *
 * @author leleact
 * @since 2021-06-07
 */
@Slf4j
class SumOfFourNumsTest {
    @Test
    void solutionTest() {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> solution = fourSum(nums, target);
        log.info("solution: {}", solution);
    }

    @Test
    void sameNumsTest() {
        int[] nums = {2, 2, 2, 2, 2};
        int target = 8;
        List<List<Integer>> solution = fourSum(nums, target);
        log.info("solution: {}", solution);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        int idx1 = 0;
        int idx2 = 0;
        int idx3 = 0;
        int idx4 = 0;
        Set<List<Integer>> sets = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();
        for (idx1 = 0; idx1 < nums.length - 3; idx1++) {
            for (idx2 = idx1 + 1; idx2 < nums.length - 2; idx2++) {
                for (idx3 = idx2 + 1; idx3 < nums.length - 1; idx3++) {
                    int last = target - nums[idx1] - nums[idx2] - nums[idx3];
                    for (idx4 = idx3 + 1; idx4 < nums.length; idx4++) {
                        if (nums[idx4] == last) {
                            List<Integer> hit = new ArrayList<>();
                            hit.add(nums[idx1]);
                            hit.add(nums[idx2]);
                            hit.add(nums[idx3]);
                            hit.add(nums[idx4]);
                            hit.sort(Integer::compareTo);
                            if (sets.contains(hit)) {
                                continue;
                            }
                            sets.add(hit);
                            result.add(hit);
                        }
                    }
                }
            }
        }
        return result;
    }
}
