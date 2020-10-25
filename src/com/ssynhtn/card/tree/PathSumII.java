package com.ssynhtn.card.tree;

import com.ssynhtn.common.TreeNode;

import java.util.*;

public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        if (root.left == null && root.right == null && root.val == sum) {
            List<Integer> path = new LinkedList<>();
            path.add(root.val);
            res.add(path);
            return res;
        }

        if (root.left != null) {
            List<List<Integer>> leftPaths = pathSum(root.left, sum - root.val);
            for (List<Integer> path : leftPaths) {
                path.add(0, root.val);
                res.add(path);
            }
        }
        if (root.right != null) {
            List<List<Integer>> rightPaths = pathSum(root.right, sum - root.val);
            for (List<Integer> path: rightPaths) {
                path.add(0, root.val);
                res.add(path);
            }
        }

        return res;

    }

    public List<List<Integer>> pathSumDFS(TreeNode root, int sum) {
        if (root == null) return Collections.emptyList();
        List<List<Integer>> res = new ArrayList<>();

        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.addLast(root);
        int total = 0;

        List<TreeNode> path = new ArrayList<>();
        while (!q.isEmpty()) {
            TreeNode node = q.removeLast();

            if (node.left == null && node.right == null) {
                if (total + node.val == sum) {
                    List<Integer> temp = new ArrayList<>();
                    for (TreeNode n : path) {
                        temp.add(n.val);
                    }
                    temp.add(node.val);
                    res.add(temp);
                }

                while (!path.isEmpty() && (path.get(path.size() - 1).right == node || path.get(path.size() - 1).right == null)) {
                    node = path.remove(path.size() - 1);
                    total -= node.val;
                }
            } else {
                path.add(node);
                total += node.val;
                if (node.right != null) {
                    q.addLast(node.right);
                }
                if (node.left != null) {
                    q.addLast(node.left);
                }
            }
        }

        return res;
    }
}
