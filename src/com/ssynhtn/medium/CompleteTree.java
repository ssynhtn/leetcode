package com.ssynhtn.medium;


import java.util.ArrayList;
import java.util.List;

public class CompleteTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        List<TreeNode> nextList = new ArrayList<>();
        List<TreeNode> temp;

        while (true) {

            int i = 0;
            TreeNode node;
            while (i < list.size()) {
                node = list.get(i);
                if (node.left != null) {
                    nextList.add(node.left);
                    if (node.right != null) {
                        nextList.add(node.right);
                        i++;
                        continue;
                    }
                } else {
                    if (node.right != null) return false;
                }

                for (i++; i < list.size(); i++) {
                    node = list.get(i);
                    if (node.left != null || node.right != null) return false;
                }
                for (TreeNode n : nextList) {
                    if (n.left != null || n.right != null) return false;
                }

                return true;

            }

            temp = list;
            list = nextList;
            nextList = temp;
            nextList.clear();

        }
    }
}

