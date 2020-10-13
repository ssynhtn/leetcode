package com.ssynhtn.mock;

import com.ssynhtn.common.TreeNode;

import java.util.ArrayDeque;

public class BinarySearchTreeTwoSum {
    public boolean findTarget(TreeNode root, int k) {
        return findTarget(root, root, k);
    }

    private boolean findTarget(TreeNode node, TreeNode root, int k) {
        if (findValue(root, k - node.val, node)) {
            return true;
        }

        if (node.left != null && findTarget(node.left, root, k)) {
            return true;
        }

        if (node.right != null && findTarget(node.right, root, k)) {
            return true;
        }
        return false;
    }

    private boolean findValue(TreeNode root, int k, TreeNode node) {
        return root != node && root.val == k || root.left != null && findValue(root.left, k, node) || root.right != null && findValue(root.right, k, node);
    }

    public boolean findTargetIter(TreeNode root, int k) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return false;

        ArrayDeque<TreeNode> left = new ArrayDeque<>();
        ArrayDeque<TreeNode> right = new ArrayDeque<>();

        addLeft(left, root.left);
        addRight(right, root.right);

        TreeNode x = left.size() > 0 ? left.removeLast() : root;
        TreeNode y = right.size() > 0 ? right.removeLast() : root;

        int sum;
        while (x != y) {
            sum = x.val + y.val;
            if (sum == k) return true;

            if (sum < k) {
                if (x != root) {
                    if (x.right == null) {
                        if (left.size() > 0) {
                            x = left.removeLast();
                        } else {
                            x = root;
                        }
                    } else {
                        addLeft(left, x.right);
                        x = left.removeLast();
                    }
                } else {
                    if (right.size() > 0) {
                        root = right.removeFirst();
                        addLeft(left, root.left);

                        if (left.size() > 0) {
                            x = left.removeLast();
                        } else {
                            x = root;
                        }
                    } else {
                        if (y.left == null) return false;
                        root = y;
                        addLeft(left, y.left);
                        x = left.removeLast();
                    }
                }
            } else {
                if (y != root) {
                    if (y.left == null) {
                        if (right.size() > 0) {
                            y = right.removeLast();
                        } else {
                            y = root;
                        }
                    } else {
                        addRight(right, y.left);
                        y = right.removeLast();
                    }
                } else {
                    if (left.size() > 0) {
                        root = left.removeFirst();
                        addRight(right, root.right);

                        if (right.size() > 0) {
                            y = right.removeLast();
                        } else {
                            y = root;
                        }
                    } else {
                        if (x.right == null) return false;
                        root = x;
                        addRight(right, x.right);
                        y = right.removeLast();
                    }
                }


            }
        }

        return false;

    }

    private void addRight(ArrayDeque<TreeNode> right, TreeNode start) {
        while (start != null) {
            right.addLast(start);
            start = start.right;
        }
    }

    private void addLeft(ArrayDeque<TreeNode> left, TreeNode start) {
        while (start != null) {
            left.addLast(start);
            start = start.left;
        }
    }
}
