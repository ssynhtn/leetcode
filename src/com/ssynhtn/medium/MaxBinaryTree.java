package com.ssynhtn.medium;

import com.ssynhtn.common.TreeNode;

public class MaxBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constRec(nums, 0, nums.length - 1);
    }

    private TreeNode constRec(int[] nums, int left, int right) {
        if (right < left) return null;
        int index = left;
        int max = nums[left];
        for (int i = left; i <= right; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }

        TreeNode node = new TreeNode(max);
        node.left = constRec(nums, left, index-1);
        node.right = constRec(nums, index+1, right);
        return node;
    }

    public TreeNode constructMaximumBinaryTreeDirect(int[] nums) {
        if (nums.length == 0) return null;
        TreeNode root = new TreeNode(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int x = nums[i];
            TreeNode node = root;
            TreeNode prev = null;

            TreeNode treeNode = new TreeNode(x);
            while (node != null && node.val > x) {
                prev = node;
                node = node.right;
            }

            treeNode.left = node;
            if (prev == null) {
                root = treeNode;
            } else {
                prev.right = treeNode;
            }
        }
        return root;
        
    }
}