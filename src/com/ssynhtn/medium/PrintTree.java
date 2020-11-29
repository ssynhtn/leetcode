package com.ssynhtn.medium;

import com.ssynhtn.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PrintTree {
    public List<List<String>> printTree(TreeNode root) {
        int h = getHeight(root);
        int w = (1 << h) - 1;
        String[][] table = new String[h][w];
        for (String[] row : table) {
            Arrays.fill(row, "");
        }

        int rootIndex = w / 2;

        placeTree(table, 0, rootIndex, root, 1 << (h-2));

        List<List<String>> res = new ArrayList<>();
        for (String[] row : table) {
            res.add(Arrays.asList(row));
        }

        return res;
    }

    /**
     *
     * @param table
     * @param i row
     * @param rootIndex index to place root
     * @param root
     */
    private void placeTree(String[][] table, int i, int rootIndex, TreeNode root, int diff) {
        table[i][rootIndex] = root.val + "";

        if (root.left != null) {
            placeTree(table, i + 1, rootIndex - diff, root.left, diff / 2);
        }

        if (root.right != null) {
            placeTree(table, i + 1, rootIndex + diff, root.right, diff / 2);
        }


    }

    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

}