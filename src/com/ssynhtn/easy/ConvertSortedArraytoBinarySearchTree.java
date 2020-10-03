package com.ssynhtn.easy;

import com.ssynhtn.common.TreeNode;

import java.util.prefs.NodeChangeEvent;

public class ConvertSortedArraytoBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums ==  null || nums.length == 0) return null;

        return convert(nums, 0, nums.length - 1);
    }

    // end >= start
    private TreeNode convert(int[] nums, int start, int end) {
        if (start == end) {
            TreeNode node = new TreeNode();
            node.val = nums[start];
            return node;
        }
        if (end == start + 1) {
            TreeNode node = new TreeNode();
            node.val = nums[start];
            TreeNode right = new TreeNode();
            right.val = nums[end];
            node.right = right;
            return node;
        }

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode();
        root.val = nums[mid];
        root.left = convert(nums, start, mid - 1);
        root.right = convert(nums, mid + 1, end);
        return root;
    }
}
