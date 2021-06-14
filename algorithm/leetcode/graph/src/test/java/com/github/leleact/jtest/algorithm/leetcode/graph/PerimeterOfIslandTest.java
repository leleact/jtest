package com.github.leleact.jtest.algorithm.leetcode.graph;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * island perimeter
 * <p>
 * leetcode No.463 for No.1034
 *
 * @author leleact
 * @since 2021-06-14
 */
@Slf4j
class PerimeterOfIslandTest {
    @Test
    void solutionTest() {
    }

    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        int i;
        int j;

        for (i = 0; i < grid.length; i++) {
            int[] row = grid[i];
            for (j = 0; j < row.length; j++) {
                if (row[j] == 1) {
                    int left = -1;
                    if (j != 0) {
                        left = row[j - 1];
                    }

                    int right = -1;
                    if (j != row.length - 1) {
                        right = row[j + 1];
                    }

                    int top = -1;
                    if (i != 0) {
                        top = grid[i - 1][j];
                    }

                    int bottom = -1;
                    if (i != grid.length - 1) {
                        bottom = grid[i + 1][j];
                    }

                    if (left == 0 || left == -1) {
                        perimeter++;
                    }

                    if (right == 0 || right == -1) {
                        perimeter++;
                    }

                    if (top == 0 || top == -1) {
                        perimeter++;
                    }

                    if (bottom == 0 || bottom == -1) {
                        perimeter++;
                    }
                }
            }
        }

        return perimeter;
    }
}
