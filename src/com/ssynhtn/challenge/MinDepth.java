package com.ssynhtn.challenge;

import com.ssynhtn.common.TreeNode;

import java.util.ArrayDeque;

public class MinDepth {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.addLast(root);
        int depth = 1;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.removeFirst();
                if (node.left != null) {
                    q.addLast(node.left);
                    if (node.right != null) {
                        q.addLast(node.right);
                    }
                } else {
                    if (node.right != null) {
                        q.addLast(node.right);
                    } else {
                        return depth;
                    }
                }
            }

            depth++;
        }


        return depth;
    }
}
