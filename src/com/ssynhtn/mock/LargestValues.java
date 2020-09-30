package com.ssynhtn.mock;

import com.ssynhtn.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class LargestValues {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> maxValues = new ArrayList<>();
        ArrayDeque<TreeNode> rows = new ArrayDeque<>();
        rows.addLast(root);

        while (!rows.isEmpty()) {
            int n = rows.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                TreeNode node = rows.removeFirst();
                if (node.val > max) {
                    max = node.val;
                }
                if (node.left != null) {
                    rows.addLast(node.left);
                }
                if (node.right != null) {
                    rows.addLast(node.right);
                }
            }

            maxValues.add(max);
        }

        return maxValues;
    }
}
