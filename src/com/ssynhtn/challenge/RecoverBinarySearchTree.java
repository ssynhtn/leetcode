package com.ssynhtn.challenge;

import com.ssynhtn.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class RecoverBinarySearchTree {
    public void recoverTree(TreeNode root) {
        List<TreeNode> collect = new ArrayList<>();
        inOrder(collect, root);

        TreeNode left = null, right = null;
        for (int i = 0; i < collect.size() - 1; i++) {
            if (collect.get(i).val > collect.get(i + 1).val) {
                left = collect.get(i);
                break;
            }
        }
        for (int i = collect.size() - 1; i >= 1; i--) {
            if (collect.get(i).val < collect.get(i - 1).val) {
                right = collect.get(i);
                break;
            }
        }

        if (left == null || right == null) return;
        int temp = left.val;
        left.val = right.val;
        right.val = temp;


    }

    private void inOrder(List<TreeNode> collect, TreeNode root) {
        if (root == null) return;
        inOrder(collect, root.left);
        collect.add(root);
        inOrder(collect, root.right);
    }
}
