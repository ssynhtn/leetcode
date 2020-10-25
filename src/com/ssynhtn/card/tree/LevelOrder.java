package com.ssynhtn.card.tree;

import com.ssynhtn.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) return res;

        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.addLast(root);

        while (!q.isEmpty()) {
            int size = q.size();
            Integer[] row = new Integer[size];
            for (int i = 0; i < size; i++) {
                TreeNode node = q.removeFirst();
                row[i] = node.val;
                if (node.left != null) {
                    q.addLast(node.left);
                }
                if (node.right != null) {
                    q.addLast(node.right);
                }
            }
            res.add(Arrays.asList(row));
        }
        return res;
    }
}
