package com.ssynhtn.easy;

import com.ssynhtn.common.TreeNode;

class MinDiffInTree {
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        int[] triple = compute(root);
        return triple[2];
    }

    // root != null, [min, max, minDiff]
    private int[] compute(TreeNode root) {
        int min = root.val;
        int max = root.val;
        int minDiff = Integer.MAX_VALUE;

        if (root.left != null) {
            int[] left = compute(root.left);
            min = left[0];
            minDiff = Math.min(root.val - left[1], minDiff);
            minDiff = Math.min(minDiff, left[2]);
        }
        if (root.right != null) {
            int[] right = compute(root.right);
            max = right[1];
            minDiff = Math.min(right[0] - root.val, minDiff);
            minDiff = Math.min(minDiff, right[2]);
        }

        return new int[]{min, max, minDiff};
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);

        System.out.println(new MinDiffInTree().getMinimumDifference(root));
    }


}