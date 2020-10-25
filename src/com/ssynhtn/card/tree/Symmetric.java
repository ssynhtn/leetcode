package com.ssynhtn.card.tree;

import com.ssynhtn.common.TreeNode;

import java.util.ArrayDeque;

public class Symmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null) return right == null;
        if (right == null) return false;
        if (left.val != right.val) return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    public boolean isSymmetricQ(TreeNode root) {
        if (root == null) return true;
        ArrayDeque<TreeNode> left = new ArrayDeque<>();
        ArrayDeque<TreeNode> right = new ArrayDeque<>();
        left.addLast(root);
        right.addLast(root);

        while (!left.isEmpty()) {
            TreeNode a = left.removeFirst();
            TreeNode b = right.removeFirst();

            if (a.val != b.val) return false;
            if ((a.left == null) != (b.right == null)) {
                return false;
            }
            if ((a.right == null) != (b.left == null)) {
                return false;
            }

            if (a.left != null) {
                left.addLast(a.left);
                right.addLast(b.right);
            }
            if (a.right != null) {
                left.addLast(a.right);
                right.addLast(b.left);
            }
        }
        return true;
    }
}
