package com.ssynhtn.medium;

public class ValidBST {
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValid(TreeNode root, int low, int high) {
        if (root == null) return true;
        if (root.val < low || root.val > high) return false;
        if (root.left != null) {
            if (root.val == Integer.MIN_VALUE) {
                return false;
            }

            if (!isValid(root.left, low, root.val - 1)) {
                return false;
            }
        }

        if (root.right != null) {
            if (root.val == Integer.MAX_VALUE) {
                return false;
            }

            if (!isValid(root.right, root.val + 1, high)) {
                return false;
            }
        }

        return true;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
