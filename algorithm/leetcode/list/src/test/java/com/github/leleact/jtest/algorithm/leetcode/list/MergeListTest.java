package com.github.leleact.jtest.algorithm.leetcode.list;

import org.junit.jupiter.api.Test;

/**
 * merge list
 *
 * @author leleact
 * @since 2021-06-13
 */
class MergeListTest {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    @Test
    void solutionTest() {
    }

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        int idx;
        ListNode first = list1;
        for (idx = 0; idx < a - 1; idx++) {
            first = first.next;
        }
        ListNode second = list1;
        for (idx = 0; idx < b; idx++) {
            second = second.next;
        }
        second = second.next;
        first.next = list2;

        ListNode last = list2;
        while (last.next != null) {
            last = last.next;
        }
        last.next = second;
        return list1;
    }
}
