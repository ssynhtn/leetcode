package com.ssynhtn.medium;

import com.ssynhtn.common.TreeNode;

public class InsertIntoBST {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode n = new TreeNode(val);
        if (root == null) return n;

        TreeNode node = root;
        while (true) {
            if (val < node.val) {
                if (node.left == null) {
                    node.left = n;
                    break;
                }
                node = node.left;
            } else {
                if (node.right == null) {
                    node.right = n;
                    break;
                }
                node = node.right;
            }
        }

        return root;
    }
}
