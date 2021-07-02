package com.github.leleact.jtest.algorithm.leetcode.binary;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * closed number
 *
 * @author leleact
 * @since 2021-06-15
 */
@Slf4j
class ClosedNumberTest {
    @Test
    void solutionTest() {
    }

    public int[] findClosedNumbers(int num) {
        int[] result = new int[2];
        if (num <= 0 || num >= Integer.MAX_VALUE) {
            result[0] = -1;
            result[1] = -1;
            return result;
        }

        result[0] = getGreater(num);
        result[1] = getLess(num);
        return result;
    }

    private int getLess(int n) {
        int temp = n;
        int c0 = 0;
        int c1 = 0;
        while ((temp & 1) == 1) {
            c1++;
            temp >>= 1;
        }

        if (temp == 0) {
            return -1;
        }

        while (((temp & 1) == 0) && (temp != 0)) {
            c0++;
            temp >>= 1;
        }

        int p = c0 + c1; // 最右边，非拖尾1的位置
        n &= ((~0) << (p + 1)); // 将位0到位p清零

        int mask = (1 << (c1 + 1)) - 1; // (c1+1)个1
        n |= mask << (c0 - 1);

        return n;
    }

    private int getGreater(int n) {
        // 计算c0和c1，用于找到最右边非拖尾0的下标p
        int c = n;
        int c0 = 0;
        int c1 = 0;
        while (((c & 1) == 0) && (c != 0)) {
            c0++;
            c >>= 1;
        }
        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }

        // 错误：若n=111111...000, 那么就没有更大的数字
        // 如果是n的二进制不存在可翻转的0，或者n就是0
        if (c0 + c1 == 31 || c0 + c1 == 0) {
            return -1;
        }

        int p = c0 + c1; // 前提：最右边，非拖尾0的位置
        n |= (1 << p); // 步骤1：翻转最右边，非拖尾0
        n &= ~((1 << p) - 1); // 步骤2：将p右方的所有位清零
        n |= (1 << (c1 - 1)) - 1; // 步骤3：在右方插入(c1-1)个1
        return n;
    }
}
