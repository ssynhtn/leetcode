package com.ssynhtn.card.tree;

import com.ssynhtn.common.TreeNode;

import java.util.ArrayDeque;

public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null) {
            if (root.right == null) {
                return root.val == sum;
            }

            return hasPathSum(root.right, sum - root.val);
        } else {
            if (root.right == null) {
                return hasPathSum(root.left, sum - root.val);
            }

            return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
        }
    }

    public boolean hasPathSumBFS(TreeNode root, int sum) {
        if (root == null) return false;
        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        ArrayDeque<Integer> v = new ArrayDeque<>();

        q.addLast(root);
        v.addLast(root.val);

        while (!q.isEmpty()) {
            TreeNode node = q.removeFirst();
            int nodeSum = v.removeFirst();

            if (nodeSum == sum && node.left == null && node.right == null) return true;
            if (node.left != null) {
                q.addLast(node.left);
                v.addLast(nodeSum + node.left.val);
            }
            if (node.right != null) {
                q.addLast(node.right);
                v.addLast(nodeSum + node.right.val);
            }
        }
        return false;
    }

}
