package com.ssynhtn.medium;

import com.ssynhtn.common.TreeNode;

public class FlattenTree {
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left == null) {
                root = root.right;
            } else {
                if (root.right != null) {
                    TreeNode node = root.left;
                    while (node.right != null) {
                        node = node.right;
                    }

                    node.right = root.right;
                }
                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }

    }
}
