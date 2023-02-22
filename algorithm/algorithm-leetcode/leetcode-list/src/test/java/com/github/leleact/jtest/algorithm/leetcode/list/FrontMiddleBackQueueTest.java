package com.github.leleact.jtest.algorithm.leetcode.list;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * FrontMiddleBackQueue test
 *
 * @author leleact
 * @since 2021-06-13
 */
class FrontMiddleBackQueueTest {
    public static class FrontMiddleBackQueue {

        private List<Integer> list;

        public FrontMiddleBackQueue() {
            list = new ArrayList<>();
        }

        public void pushFront(int val) {
            List<Integer> temp = new ArrayList<>();
            temp.add(val);
            temp.addAll(list);
            list = temp;
        }

        public void pushMiddle(int val) {
            if (list.size() == 0) {
                list.add(val);
                return;
            }

            if (list.size() == 1) {
                pushFront(val);
                return;
            }
            int size = list.size();
            int pos = size / 2;
            List<Integer> temp = new ArrayList<>(list.subList(0, pos));
            temp.add(val);
            temp.addAll(list.subList(pos, list.size()));
            list = temp;
        }

        public void pushBack(int val) {
            list.add(val);
        }

        public int popFront() {
            if (list.size() == 0) {
                return -1;
            }
            return list.remove(0);
        }

        public int popMiddle() {
            if (list.size() == 0) {
                return -1;
            }
            int size = list.size();
            int pos;
            if (size % 2 == 0) {
                pos = size / 2;
            } else {
                pos = size / 2 + 1;
            }
            return list.remove(pos - 1);
        }

        public int popBack() {
            if (list.size() == 0) {
                return -1;
            }
            return list.remove(list.size() - 1);
        }
    }

    @Test
    void solution() {
        FrontMiddleBackQueue q = new FrontMiddleBackQueue();
        q.pushFront(1);   // [1]
        q.pushBack(2);    // [1, 2]
        q.pushMiddle(3);  // [1, 3, 2]
        q.pushMiddle(4);  // [1, 4, 3, 2]
        q.popFront();     // 返回 1 -> [4, 3, 2]
        q.popMiddle();    // 返回 3 -> [4, 2]
        q.popMiddle();    // 返回 4 -> [2]
        q.popBack();      // 返回 2 -> []
        q.popFront();     // 返回 -1 -> [] （队列为空）
    }
}
