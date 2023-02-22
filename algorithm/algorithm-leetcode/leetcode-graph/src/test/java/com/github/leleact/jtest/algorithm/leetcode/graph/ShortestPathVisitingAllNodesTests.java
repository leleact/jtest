package com.github.leleact.jtest.algorithm.leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Shortest Path Visiting All Nodes
 * <p>
 * Leetcode No.847
 *
 * @author leleact
 * @since 2021-08-06
 */
public class ShortestPathVisitingAllNodesTests {
    public static class Solution {
        public int shortestPathLength(int[][] graph) {
            int n = graph.length;
            Queue<int[]> queue = new LinkedList<int[]>();
            boolean[][] seen = new boolean[n][1 << n];
            for (int i = 0; i < n; ++i) {
                queue.offer(new int[]{i, 1 << i, 0});
                seen[i][1 << i] = true;
            }

            int ans = 0;
            while (!queue.isEmpty()) {
                int[] tuple = queue.poll();
                int u = tuple[0], mask = tuple[1], dist = tuple[2];
                if (mask == (1 << n) - 1) {
                    ans = dist;
                    break;
                }
                // 搜索相邻的节点
                for (int v : graph[u]) {
                    // 将 mask 的第 v 位置为 1
                    int maskV = mask | (1 << v);
                    if (!seen[v][maskV]) {
                        queue.offer(new int[]{v, maskV, dist + 1});
                        seen[v][maskV] = true;
                    }
                }
            }
            return ans;
        }
    }

    @Test
    public void solutionTest() {
        Assertions.assertEquals(4, new Solution().shortestPathLength(new int[][]{{1, 2, 3}, {0}, {0}, {0}}));
        Assertions.assertEquals(4, new Solution().shortestPathLength(new int[][]{{1}, {0, 2, 4}, {1, 3, 4}, {2}, {1, 2}}));
    }
}
