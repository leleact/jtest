package com.github.leleact.jtest.algorithm.leetcode.sort;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * k closest
 *
 * @author leleact
 * @since 2021-06-11
 */
@Slf4j
class KClosestTest {
    @Test
    void solutionTest() {
        int[][] points = new int[][]{{3, 3}, {3, -3}, {-3, -3}};
        int k = 2;
        int[][] result = kClosest(points, k);
        log.info("result: {}", Arrays.stream(result).toArray());
    }

    public int[][] kClosest(int[][] points, int k) {
        class Key implements Comparable<Key> {
            private int distant;
            private int idx;

            public Key(int distant, int idx) {
                this.distant = distant;
                this.idx = idx;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Key key = (Key) o;
                return distant == key.distant && idx == key.idx;
            }

            @Override
            public int hashCode() {
                return Objects.hash(distant, idx);
            }

            @Override
            public int compareTo(Key o) {
                if (distant < o.distant) {
                    return -1;
                }

                if (distant == o.distant) {
                    return idx - o.idx;
                }
                return 1;
            }
        }

        TreeMap<Key, int[]> map = new TreeMap<>();
        int i;
        for (i = 0; i < points.length; i++) {
            int distant = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            map.put(new Key(distant, i), points[i]);
        }

        int[][] result = new int[k][2];
        for (i = 0; i < k; i++) {
            Map.Entry<Key, int[]> entry = map.pollFirstEntry();
            result[i] = entry.getValue();
        }
        return result;
    }
}
