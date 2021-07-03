package com.github.leleact.jtest.algorithm.leetcode.sort;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * sort characters by frequency
 * <p>
 * leetcode No.451
 *
 * @author leleact
 * @since 2021-07-03
 */
@Slf4j
public class SortCharactersByFrequencyTests {
    class Solution {
        class CharacterCount implements Comparable<CharacterCount> {
            private Integer count;

            private Character character;

            public CharacterCount(Integer count, Character character) {
                this.count = count;
                this.character = character;
            }

            @Override
            public int compareTo(CharacterCount o) {
                if (count > o.count) {
                    return -1;
                }

                if (count < o.count) {
                    return 1;
                }

                return this.character.compareTo(o.character);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                CharacterCount that = (CharacterCount) o;
                return Objects.equals(count, that.count) && Objects.equals(character, that.character);
            }

            @Override
            public int hashCode() {
                return Objects.hash(count, character);
            }
        }

        public String frequencySort(String s) {
            Map<Character, Integer> countMap = new HashMap<>();
            char[] chars = s.toCharArray();
            int idx;
            for (idx = 0; idx < chars.length; idx++) {
                if (null == countMap.get(chars[idx])) {
                    countMap.put(chars[idx], 1);
                } else {
                    int count = countMap.get(chars[idx]);
                    count++;
                    countMap.put(chars[idx], count);
                }
            }

            TreeMap<CharacterCount, Object> treeMap = new TreeMap<>();
            countMap.forEach((c, count) -> {
                treeMap.put(new CharacterCount(count, c), new Object());
            });

            int len = 0;
            for (Map.Entry<CharacterCount, Object> entry : treeMap.entrySet()) {
                Arrays.fill(chars, len, entry.getKey().count + len, entry.getKey().character);
                len = len + entry.getKey().count;
            }
            return new String(chars);
        }
    }

    class Solution1 {
        public String frequencySort(String s) {
            Map<Character, Integer> countMap = new HashMap<>();
            char[] chars = s.toCharArray();
            int idx;
            for (idx = 0; idx < chars.length; idx++) {
                char c = chars[idx];
                int frequency = countMap.getOrDefault(c, 0) + 1;
                countMap.put(c, frequency);
            }
            List<Character> list = new ArrayList<Character>(countMap.keySet());
            list.sort((a, b) -> countMap.get(b) - countMap.get(a));
            StringBuilder sb = new StringBuilder();
            int size = list.size();
            for (char c : list) {
                int frequency = countMap.get(c);
                sb.append(String.valueOf(c).repeat(Math.max(0, frequency)));
            }
            return sb.toString();
        }
    }

    @Test
    public void solutionTest() {
        String s = "acccba";
        String result = new Solution().frequencySort(s);
        log.info("result: {}", result);
    }
}
