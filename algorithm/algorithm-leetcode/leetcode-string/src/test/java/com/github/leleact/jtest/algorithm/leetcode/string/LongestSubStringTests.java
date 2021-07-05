package com.github.leleact.jtest.algorithm.leetcode.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

/**
 * Longest Substring Without Repeating Characters
 * <p>
 * leetcode No.3
 *
 * @author leleact
 * @since 2021-07-05
 */
@Slf4j
public class LongestSubStringTests {
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int i;
            int j;
            int max = 0;
            for (i = 0; i < s.length(); i++) {
                HashSet<Character> hashSet = new HashSet<>();
                for (j = i; j < s.length(); j++) {
                    char c = s.charAt(j);
                    if (hashSet.contains(c)) {
                        break;
                    }
                    hashSet.add(c);
                }
                if (hashSet.size() > max) {
                    max = hashSet.size();
                }
            }
            return max;
        }
    }

    @Test
    public void solutionTest() {
        // TODO Sliding Window
        String s = "pwwkew";
        int max = new Solution().lengthOfLongestSubstring(s);
        log.info("max: {}", max);
    }
}
