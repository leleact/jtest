package com.github.leleact.jtest.algorithm.leetcode.graph;

import org.junit.jupiter.api.Test;

/**
 * border color
 * <p>
 * leetcode No.1034
 *
 * @author leleact
 * @since 2021-06-14
 */
class BorderColorTest {
    private int[] d = {0, 1, 0, -1, 0};

    @Test
    void solutionTest() {
    }

    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        dfs(grid, r0, c0, grid[r0][c0]);
        for (int[] g : grid) {
            for (int i = 0; i < g.length; ++i) {
                if (g[i] < 0) {
                    g[i] = color;
                }
            }
        }
        return grid;
    }

    private void dfs(int[][] grid, int r, int c, int clr) {
        grid[r][c] = -clr;
        int cnt = 0;
        for (int i = 0; i < 4; ++i) {
            int x = r + d[i], y = c + d[i + 1];
            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || Math.abs(grid[x][y]) != clr) {
                continue;
            }
            ++cnt;
            if (grid[x][y] == clr) {
                dfs(grid, x, y, clr);
            }
        }
        if (cnt == 4) {
            grid[r][c] = clr;
        }
    }
}
