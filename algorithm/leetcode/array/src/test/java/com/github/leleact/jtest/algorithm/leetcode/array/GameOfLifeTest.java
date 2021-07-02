package com.github.leleact.jtest.algorithm.leetcode.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * game of life
 * <p>
 * leetcode No.289
 *
 * @author leleact
 * @since 2021-06-17
 */
@Slf4j
class GameOfLifeTest {
    @Test
    void solutionTest() {
        int[][] board = new int[][]{
            {0, 1, 0},
            {0, 0, 1},
            {1, 1, 1},
            {0, 0, 0}
        };
        gameOfLife(board);
    }

    public void gameOfLife(int[][] board) {
        int[][] result = new int[board.length][board[0].length];
        int m;
        int n;
        for (m = 0; m < board.length; m++) {
            for (n = 0; n < board[0].length; n++) {
                int[] around = new int[]{0, 0};
                int i1;
                if (m - 1 < 0 || n - 1 < 0) {
                    i1 = -1;
                } else {
                    i1 = board[m - 1][n - 1];
                }
                around = calc(around, i1);

                int i2;
                if (m - 1 < 0) {
                    i2 = -1;
                } else {
                    i2 = board[m - 1][n];
                }
                around = calc(around, i2);

                int i3;
                if (m - 1 < 0 || n + 1 >= board[0].length) {
                    i3 = -1;
                } else {
                    i3 = board[m - 1][n + 1];
                }
                around = calc(around, i3);

                int i4;
                if (n - 1 < 0) {
                    i4 = -1;
                } else {
                    i4 = board[m][n - 1];
                }
                around = calc(around, i4);

                int i5;
                if (n + 1 >= board[0].length) {
                    i5 = -1;
                } else {
                    i5 = board[m][n + 1];
                }
                around = calc(around, i5);

                int i6;
                if (m + 1 >= board.length || n - 1 < 0) {
                    i6 = -1;
                } else {
                    i6 = board[m + 1][n - 1];
                }
                around = calc(around, i6);


                int i7;
                if (m + 1 >= board.length) {
                    i7 = -1;
                } else {
                    i7 = board[m + 1][n];
                }
                around = calc(around, i7);

                int i8;
                if (m + 1 >= board.length || n + 1 >= board[0].length) {
                    i8 = -1;
                } else {
                    i8 = board[m + 1][n + 1];
                }
                around = calc(around, i8);

                result[m][n] = board[m][n];

                if (board[m][n] == 1 && around[0] < 2) {
                    result[m][n] = 0;
                } else if (board[m][n] == 1 && (around[0] == 2 || around[0] == 3)) {
                    result[m][n] = 1;
                } else if (board[m][n] == 1 && around[0] > 3) {
                    result[m][n] = 0;
                } else if (board[m][n] == 0 && around[0] == 3) {
                    result[m][n] = 1;
                }
            }
        }
        for (m = 0; m < board.length; m++) {
            for (n = 0; n < board[0].length; n++) {
                board[m][n] = result[m][n];
            }
        }
    }

    private int[] calc(int[] calc, int value) {
        if (value != -1 && (value & 1) == 1) {
            calc[0]++;
        } else if (value != -1 && (value & 1) == 0) {
            calc[1]++;
        }
        return calc;
    }
}
