package com.ssynhtn.card.tree;

import com.ssynhtn.common.TreeNode;

class MaxDepth {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}