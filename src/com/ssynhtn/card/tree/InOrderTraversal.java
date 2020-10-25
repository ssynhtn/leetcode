package com.ssynhtn.card.tree;

import com.ssynhtn.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class InOrderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) return res;
        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        TreeNode node = root;

        while (node != null) {
            q.addLast(node);
            node = node.left;
        }

        while (!q.isEmpty()) {
            node = q.removeLast();
            res.add(node.val);
            node = node.right;
            while (node != null) {
                q.addLast(node.left);
                node = node.left;
            }
        }

        return res;
    }
    public List<Integer> inorderTraversalRec(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        inOrder(root, res);

        return res;

    }

    private void inOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inOrder(root.left, res);
        res.add(root.val);
        inOrder(root.right, res);
    }
}
