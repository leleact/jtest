package com.github.leleact.jtest.algorithm.leetcode.graph;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * shortest path with alternating colors
 * <p>
 * leetcode No.1129
 *
 * @author leleact
 * @since 2021-06-08
 */
@Slf4j
class ShortestPathWithAlternatingColorsTest {
    @Test
    void solutionTest() {
        int[][] redEdges = {{0, 1}, {1, 2}};
        int[][] blueEdges = {};
        int n = 3;
        int[] answer = shortestAlternatingPaths(n, redEdges, blueEdges);

        Assertions.assertArrayEquals(new int[]{
            0, 1, -1
        }, answer);
        log.info("answer: {}", answer);
    }

    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        int[] result = new int[n];
        result[0] = 0;
        int i;
        for (i = 1; i < n; i++) {
        }
        return result;
    }
}
