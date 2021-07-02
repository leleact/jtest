package com.github.leleact.jtest.algorithm.common.tree;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * tree
 *
 * @author leleact
 * @since 2021-06-30
 */
@Slf4j
public class TreeTests {
    public static class Node<T> {
        private Node<T> left;
        private T val;
        private Node<T> right;

        public Node(T value) {
            val = value;
        }
    }

    @Test
    public void preOrderTest() {
        //           1
        //     2            3
        // 4      5       6     7
        //      8   9
        Node<Integer> head = new Node<>(1);
        head.left = new Node<>(2);
        head.right = new Node<>(3);
        head.left.left = new Node<>(4);
        head.left.right = new Node<>(5);
        head.left.right.left = new Node<>(8);
        head.left.right.right = new Node<>(9);
        head.right.left = new Node<>(6);
        head.right.right = new Node<>(7);
        StringBuilder sb = new StringBuilder();
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            Node<Integer> n = queue.poll();
            sb.append(n.val).append(",");
            if (n.left != null) {
                queue.offer(n.left);
            }
            if (n.right != null) {
                queue.offer(n.right);
            }
        }
        log.info("result: {}", sb.substring(0, sb.length() - 1));
    }
}
