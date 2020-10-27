package com.ssynhtn.medium;

import com.ssynhtn.common.Node;

import java.util.ArrayDeque;

public class PopulatingNextRightPointersinEachNode {
    public Node connect(Node root) {
        if (root != null) {
            connect(root.left);
            connect(root.right);

            Node left = root.left;
            Node right = root.right;

            while (left != null) {
                left.next = right;
                left = left.right;
                right = right.left;
            }
        }
        return root;
    }

    public Node connectBFS(Node root) {
        if (root == null) return null;

        ArrayDeque<Node> q = new ArrayDeque<>();
        q.addLast(root);

        while (!q.isEmpty()) {
            int size = q.size();
            Node prev = null;
            for (int i = 0; i < size; i++) {
                Node node = q.removeFirst();
                if (prev != null) {
                    prev.right = node;
                }
                prev = node;

                if (prev.left != null) {
                    q.addLast(prev.left);
                }
                if (prev.right != null) {
                    q.addLast(prev.right);
                }
            }
        }
        return root;
    }

    public Node connectLevel(Node root) {
        Node leftMost = root;
        while (leftMost != null) {
            Node start = leftMost;
            Node prev = null;
            while (start != null) {
                if (start.left != null) {
                    if (prev != null) {
                        prev.next = start.left;
                    }
                    prev = start.left;
                }

                if (start.right != null) {
                    if (prev != null) {
                        prev.next = start.right;
                    }
                    prev = start.right;
                }

                start = start.next;
            }

            while (leftMost != null && leftMost.left == null && leftMost.right == null) {
                leftMost = leftMost.next;
            }

            if (leftMost != null) {
                if (leftMost.left != null) {
                    leftMost = leftMost.left;
                } else {
                    leftMost = leftMost.right;
                }
            }
        }

        return root;
    }
}
